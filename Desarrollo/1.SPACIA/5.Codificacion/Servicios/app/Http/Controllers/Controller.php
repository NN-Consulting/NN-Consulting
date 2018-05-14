<?php

namespace App\Http\Controllers;

use App\Exceptions\ResourceNotFoundException;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Http\Response;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;

class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    /**
     * Check the existence of the resource.
     *
     * @param $object
     * @param string $modelName
     * @throws \Throwable
     */
    public function checkExistence($object, $modelName = "")
    {
        throw_if(is_null($object), new ResourceNotFoundException($modelName));
    }

    /**
     * Json formatter response helper.
     *
     * @param $data
     * @param int $statusCode
     * @param null $message
     * @param array $headers
     * @return \Illuminate\Http\JsonResponse
     */
    public function jsonResponse($data, $statusCode = Response::HTTP_OK, $message = null, $headers = [])
    {
        $json = [];

        $json = ($message != null)
            ? $json = array_merge($json, ['message' => $message])
            : $json;

        $json = ( ! empty($data))
            ? $json = array_merge($json, ['data' => $data])
            : $json;

        return response()->json($json, $statusCode, $headers);
    }
}
