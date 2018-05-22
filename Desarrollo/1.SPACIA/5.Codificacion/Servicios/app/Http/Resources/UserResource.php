<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\Resource;

class UserResource extends Resource {

    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'user_id'      => $this->user_id,
            'first_name'   => $this->first_name,
            'last_name'    => $this->last_name,
            'email'        => $this->email,
        ];
    }
}
