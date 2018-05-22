<?php

namespace App\Http\Controllers;

use App\Http\Requests\Acl\ShowUserAclRequest;
use App\Http\Requests\Profile\UpdatePasswordRequest;
use App\Http\Requests\User\CreateUserRequest;
use App\Http\Requests\Profile\GetProfileRequest;
use App\Http\Requests\User\deleteUserRequest;
use App\Http\Requests\User\GetUserRequest;
use App\Http\Requests\User\NewUserRequest;
use App\Http\Requests\Profile\UpdateProfileRequest;
use App\Http\Requests\User\UpdateUserRequest;
use App\Http\Resources\PermissionResource;
use App\Http\Resources\RoleResource;
use App\Http\Resources\UserResource;
use App\Mail\NewUserWelcomeEmail;
use App\Repositories\UserRepository;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Hash;
use Tymon\JWTAuth\Facades\JWTAuth;

/**
 * Class UserController
 *
 * @package App\Http\Controllers
 */
class UserController extends Controller {

    /**
     * @var UserRepository
     */
    private $users;

    /**
     * UserController constructor.
     *
     * @param UserRepository $users
     */
    public function __construct(UserRepository $users)
    {
        $this->users = $users;
    }

    /**
     * List the Users of the system.
     *
     * @param GetUserRequest $request
     * @return \Illuminate\Http\Resources\Json\AnonymousResourceCollection
     */
    public function index(GetUserRequest $request)
    {
        $pageSize = $request->paginate ? $request->paginate : 10;

        if ($request->has('q'))
            $users = $this->users->search($request->get('q'))->paginate($pageSize);
        else
            $users = $this->users->paginate($pageSize);

        return UserResource::collection($users);
    }

    /**
     * Create User.
     *
     * @param CreateUserRequest $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function store(CreateUserRequest $request)
    {
        $data = $this->getUserData($request);
        $user = $this->users->create($data);
        $user->attachRole('user');

        return response()->json([
            "message" => "The user has been successfully added into the system.",
            "data"    => new UserResource($user)
        ], Response::HTTP_CREATED);
    }

    /**
     * Return a specific User.
     *
     * @param GetProfileRequest $request
     * @return \Illuminate\Contracts\Routing\ResponseFactory|\Symfony\Component\HttpFoundation\Response
     * @throws \Throwable
     */
    public function show(GetProfileRequest $request)
    {
        $user = $this->checkUserExistence($request);

        return $this->returnUser($user);
    }

    /**
     * New User sign-up.
     *
     * @param NewUserRequest $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function register(NewUserRequest $request)
    {
        $data = $this->getUserData($request);
        $user = $this->users->create($data);
        $token = JWTAuth::fromUser($user);
        $user->attachRole('user');

        Mail::to($user)->send(new NewUserWelcomeEmail($user));

        return response()->json([
            "message" => "The user has been successfully registered.",
            "data"    => [
                "token_type"   => "Bearer",
                "access_token" => $token
            ]
        ], Response::HTTP_CREATED);
    }

    /**
     * Update a User details.
     *
     * @param UpdateUserRequest $request
     * @return \Illuminate\Http\JsonResponse
     * @throws \Throwable
     */
    public function update(UpdateUserRequest $request)
    {
        $user = $this->checkUserExistence($request);

        $data = $this->getUserData($request);
        $this->users->update($data, $user);

        return response()->json([
            "data" => new UserResource($user->fresh())
        ], Response::HTTP_OK);
    }

    /**
     * Delete the given User.
     *
     * @param deleteUserRequest $request
     * @return \Illuminate\Http\JsonResponse
     * @throws \Throwable
     */
    public function destroy(DeleteUserRequest $request)
    {
        $user = $this->checkUserExistence($request);
        $this->users->delete($user);

        return response()->json(['message' => 'The user has been deleted.'],
            Response::HTTP_OK);
    }

    /**
     * Get the logged-in User profile.
     *
     * @param GetProfileRequest $request
     * @return \Illuminate\Contracts\Routing\ResponseFactory|\Symfony\Component\HttpFoundation\Response
     */
    public function getProfile(GetProfileRequest $request)
    {
        $user = $this->users->find(auth()->id());

        return $this->returnUser($user);
    }

    /**
     * Update the logged-in User profile details.
     *
     * @param UpdateProfileRequest $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function updateProfile(UpdateProfileRequest $request)
    {
        $user = $this->users->find(auth()->id());
        $data = $this->getUserData($request);
        $this->users->update($data, $user);

        return response()->json([
            "data" => new UserResource($user->fresh())
        ], Response::HTTP_OK);
    }

    /**
     * Get the valid User data from the given Request.
     *
     * @param Request $request
     * @return array
     */
    public function getUserData(Request $request): array
    {
        $data = $request->only(
            'name', 'email', 'password', 'first_name',
            'last_name', 'email', 'birthday', 'phone_number'
        );

        if (isset($data['password']))
        {
            $data['password'] = password_hash($data['password'], PASSWORD_BCRYPT);
        }

        return $data;
    }

    /**
     * Format the User response.
     *
     * @param $user
     * @return \Illuminate\Contracts\Routing\ResponseFactory|\Symfony\Component\HttpFoundation\Response
     */
    public function returnUser($user)
    {
        return response([
            "data" => new UserResource($user)
        ], Response::HTTP_OK);
    }

    /**
     * Return the Roles and Permissions of a specific User.
     *
     * @param ShowUserAclRequest $request
     * @return \Illuminate\Http\JsonResponse
     * @throws \Throwable
     */
    public function showAcl(ShowUserAclRequest $request)
    {
        $user = $this->checkUserExistence($request);

        return response()->json([
            "data" => [
                "roles"       => RoleResource::collection($user->roles),
                "permissions" => PermissionResource::collection($user->allPermissions())
            ]
        ]);
    }

    /**
     * Update a logged-in User password.
     *
     * @param UpdatePasswordRequest $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function updatePassword(UpdatePasswordRequest $request)
    {
        $data = $request->only('old_password', 'new_password');
        $user = auth()->user();

        if ( ! Hash::check($data['old_password'], $user->password))
            return response()->json(['message' => 'The old password is invalid.'],
                Response::HTTP_UNPROCESSABLE_ENTITY);

        $this->users->update(['password' => bcrypt($data['new_password'])], $user);

        return $this->jsonResponse(['message' => 'La contraseña ha sido actualizada.'], Response::HTTP_OK, 'The password has been successfully updated.');
    }

    /**
     * @param Request $request
     * @return mixed
     * @throws \Throwable
     */
    public function checkUserExistence(Request $request)
    {
        $user = $this->users->find($request->user_id);
        $this->checkExistence($user, 'user');

        return $user;
    }
}