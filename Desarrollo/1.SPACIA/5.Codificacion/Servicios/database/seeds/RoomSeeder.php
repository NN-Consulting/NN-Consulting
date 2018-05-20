<?php

use Illuminate\Database\Seeder;

class RoomSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        factory(App\Room::class)->create(['name' => 'Sala de conferencias', 'capacity' => 38]);
        factory(App\Room::class)->create(['name' => 'Sala de reuniones #1', 'capacity' => 6]);
        factory(App\Room::class)->create(['name' => 'Sala de reuniones #2', 'capacity' => 6]);
        factory(App\Room::class)->create(['name' => 'Sala de proyecciones', 'capacity' => 14]);
        factory(App\Room::class)->create(['name' => 'Salón de relajación', 'capacity' => 8]);
        factory(App\Room::class)->create(['name' => 'Comedor', 'capacity' => 30]);

    }
}

