<?php

use Illuminate\Database\Seeder;

class EventDetailsSeeder extends Seeder {

    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $resources = array_rand([1, 2, 3, 4, 5, 6, 7], random_int(1, 7));
        $attendants = array_rand([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], random_int(1, 6));
        $events = App\Event::all()->map(function ($event) use ($resources, $attendants) {
            $event->attendants()->attach($attendants);
            $event->resources()->attach($resources);
        });
    }
}
