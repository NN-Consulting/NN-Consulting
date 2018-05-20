<?php

use Illuminate\Database\Seeder;

class TeamSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        factory(App\Team::class)->create(['name' => 'Desarrolladores']);
        factory(App\Team::class)->create(['name' => 'Analistas']);
        factory(App\Team::class)->create(['name' => 'Diseñadores']);
        factory(App\Team::class)->create(['name' => 'Administrativos']);
    }
}

