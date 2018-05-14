<?php

namespace App\Http\Controllers;

use App\Event;
use App\Http\Requests\Events\GetEventsRequest;
use App\Http\Resources\EventResource;
use App\Http\Resources\RoomResource;
use App\Room;
use Illuminate\Http\Request;
use Illuminate\Support\Carbon;
use Illuminate\Support\Facades\DB;

class EventController extends Controller {

    public function index(GetEventsRequest $request)
    {
        if ($request->has('room_id'))
        {
            $events = Event::whereDate('date', Carbon::createFromFormat('d/m/Y', $request->get('date'))
                ->format('Y-m-d'))
                ->where('room_id', $request->get('room_id'))
                ->get();

            return EventResource::collection($events);
        }
        else
        {
            $events = $this->getEventsByDate($request);

            return $this->jsonResponse(['events' => $events, 'rooms' => RoomResource::collection(Room::all())]);
        }
    }

    public function getEventsByDate(Request $request)
    {
        $events = [];
        $starDate = clone Carbon::createFromFormat('d/m/Y', $request->get('date'))
            ->startOfWeek();

        for ($i = 1; $i <= 7; $i++)
        {
            $dailyEvents = Event::getQuery()
                ->whereDate('date', $starDate)
                ->select('room_id', DB::raw('count(*) as num_events'))
                ->groupBy('room_id')
                ->get();

            array_push($events, ['date' => (clone $starDate)->format('d/m/Y'), 'details' => $dailyEvents]);
            $starDate->addDay();
        }

        return $events;
    }
}
