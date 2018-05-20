<?php

namespace App\Http\Controllers;

use App\Http\Requests\Auth\LoginRequest;
use App\User;
use App\Repositories\UserRepository;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Auth;
use Tymon\JWTAuth\Facades\JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;


/**
 * Class AuthController
 *
 * @package App\Http\Controllers
 */
class AuthController extends Controller {

    /**
     * @var UserRepository
     */
    private $users;

    /**
     * AuthController constructor.
     *
     * @param UserRepository $users
     */
    public function __construct(UserRepository $users)
    {
        $this->users = $users;
    }

    /**
     * Authenticate a user depending of his role. Available roles: user, employee, manager & admin.
     *
     * @param LoginRequest $request
     * @return mixed
     */
    public function login(LoginRequest $request)
    {
        $token = $this->loginAttempt($request);
        $this->checkRole($role = $request->role);
        $data = $this->getLoginResponseData($role, $token);

        return $this->jsonResponse($data, Response::HTTP_OK,
            'The user has successfully logged in.');
    }


    /**
     * Authenticates a user by his Facebook's access_token.
     *
     * @param FacebookLoginRequest $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function facebookLogin(FacebookLoginRequest $request)
    {
        $socialUser = $this->fbService->getUserInfoFromFB($request->social_token);

        $user = User::where('facebook_id', '=', $socialUser->id)->first();

        if ( ! is_null($user))
        {
            $this->updateUserPicture($socialUser, $user);

            return $this->loginSocialUser($user);
        }

        $user = User::where('email', '=', $socialUser->email)->first();

        if ( ! is_null($user))
        {
            $this->linkFacebookToExistingUser($user, $socialUser);

            return $this->loginSocialUser($user);
        }

        $newUser = $this->fbService->createUserObjectFromFB($socialUser);

        return $this->jsonResponse($newUser, Response::HTTP_ACCEPTED,
            'The username is not registered yet.');
    }

    /**
     * Logout of the system.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function logout()
    {
        Auth::logout();

        return response()->json(['message' => 'The user has successfully logout of the system.'],
            Response::HTTP_OK);
    }

    /**
     * Attempt to login, return access_token.
     *
     * @param LoginRequest $request
     * @return \Illuminate\Http\JsonResponse|string
     */
    public function loginAttempt(LoginRequest $request)
    {
        $credentials = $request->only('email', 'password');

        try
        {
            if ( ! $token = JWTAuth::attempt($credentials))
                abort(Response::HTTP_UNAUTHORIZED, 'Invalid credentials.');

            elseif ($request->has('access'))
                if ( ! auth()->user()->hasRole($request->get('access')))
                    abort(Response::HTTP_UNAUTHORIZED,
                        'This user is unauthorized to perform this action.');
        }
        catch (JWTException $e)
        {
            abort(Response::HTTP_INTERNAL_SERVER_ERROR, 'Could not create token.');
        }

        return $token;
    }

    /**
     * Update user picture with a his/her Facebook avatar.
     *
     * @param $socialUser
     * @param $user
     */
    public function updateUserPicture($socialUser, $user)
    {
        $this->users->updateImage($this->fbService->getPictureFromFacebookUser($socialUser)
            , "jpg", $user);
    }

    /**
     * Link the User account with its Facebook id.
     *
     * @param $user
     * @param $socialUser
     */
    public function linkFacebookToExistingUser($user, $socialUser)
    {
        $this->users->update(['facebook_id' => $socialUser->id], $user);
    }

    /**
     * Get the User access to token and format the response to be returned.
     *
     * @param $user
     * @return \Illuminate\Http\JsonResponse
     */
    public function loginSocialUser($user)
    {
        $token = JWTAuth::fromUser($user);

        return $this->jsonResponse(['token_type' => 'Bearer', 'access_token' => $token],
            Response::HTTP_OK, 'The user has successfully logged in.');
    }

    /**
     * Check if the User has the given Role.
     *
     * @param $role
     */
    private function checkRole($role)
    {
        if ( ! is_null($role))
        {
            if ($role === 'client')
            {
                if (auth()->user()->hasRole('employee') OR auth()->user()->hasRole('manager'))
                    return;
            }
            else
            {
                if (auth()->user()->hasRole($role))
                    return;
            }

            abort(Response::HTTP_UNAUTHORIZED, 'The user is not authorized to access.');
        }
    }

    /**
     * Add the User venues in case is an employee/manager.
     *
     * @param $role
     * @param string $token
     * @return array
     */
    public function getLoginResponseData($role, string $token): array
    {
        $data = ['token_type' => 'Bearer', 'access_token' => $token];

        if ($role == 'client')
        {
            $data['venues'] = VenueResource::collection(auth()->user()->venues);
        }

        return $data;
    }
}