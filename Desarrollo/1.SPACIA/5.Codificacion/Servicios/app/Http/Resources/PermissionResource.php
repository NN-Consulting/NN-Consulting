<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\Resource;

class PermissionResource extends Resource {

    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'permission_id' => $this->permission_id,
            'name'          => $this->name,
            'display_name'  => $this->display_name,
            'description'   => $this->description
        ];
    }
}
