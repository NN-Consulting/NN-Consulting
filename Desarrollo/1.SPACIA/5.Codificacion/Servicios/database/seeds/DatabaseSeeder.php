<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
         $this->call(LaratrustSeeder::class);

        $this->call(UserSeeder::class);
        $this->call(TeamSeeder::class);
        $this->call(ResourceSeeder::class);
        $this->call(RoomSeeder::class);
        $this->call(EventSeeder::class);
        $this->call(EventDetailsSeeder::class);
    }
}
