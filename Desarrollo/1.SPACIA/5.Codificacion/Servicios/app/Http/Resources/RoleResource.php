<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\Resource;

class RoleResource extends Resource {

    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'role_id'      => $this->role_id,
            'name'         => $this->name,
            'display_name' => $this->display_name,
            'description'  => $this->description,
            'permissions'  => PermissionResource::collection($this->whenLoaded('permissions'))
        ];
    }
}
