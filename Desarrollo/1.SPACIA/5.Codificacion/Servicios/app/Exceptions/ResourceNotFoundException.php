<?php

namespace App\Exceptions;

use Exception;
use Illuminate\Http\Response;
use Throwable;

class ResourceNotFoundException extends Exception
{
    public $model;

    public function __construct($model = "", $message = "", $code = 0, Throwable $previous = null)
    {
        parent::__construct($message, $code, $previous);
        $this->model = $model;

    }


    public function render()
    {
        $model = $this->model != "" ? $this->model : "resource";

        return response()->json([
            "message" => "The " . $model . " you are trying to access does not exist.",
        ], Response::HTTP_NOT_FOUND);
    }
}
