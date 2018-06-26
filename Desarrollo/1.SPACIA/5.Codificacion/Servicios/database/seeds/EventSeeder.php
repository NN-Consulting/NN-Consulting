<?php

use Illuminate\Database\Seeder;

class EventSeeder extends Seeder {

    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $events = \App\Room::all()->map(function ($room) {
            for ($days = 1; $days < 60; $days++)
            {
                $events = random_int(0, 3);
                if ($events != 0)
                {
                    factory(App\Event::class, $events)->create([
                        'room_id' => $room->room_id,
                        'date'    => now()->addDay($days),
                    ]);
                }
            }
        });
    }
}
