<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class EventResource extends JsonResource {

    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request $request
     * @return array
     */
    public function toArray($request)
    {
        return [
            'event_id'       => $this->event_id,
            'name'           => $this->name,
            'room'           => $this->room,
            'date'           => $this->date->format('d/m/Y'),
            'hour_from'      => $this->hour_from,
            'hour_to'        => $this->hour_to,
            'num_attendants' => $this->attendants()->count(),
            'num_resources'  => $this->resources()->count(),
            'attendants'     => UserResource::collection($this->attendants),
            'resources'      => ResourceResource::collection(($this->resources)),
        ];
    }
}
