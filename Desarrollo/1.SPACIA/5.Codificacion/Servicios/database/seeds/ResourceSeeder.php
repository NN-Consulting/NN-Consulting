<?php

use Illuminate\Database\Seeder;

class ResourceSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        factory(App\Resource::class)->create(['name' => 'Proyector']);
        factory(App\Resource::class)->create(['name' => 'Refrigerio']);
        factory(App\Resource::class)->create(['name' => 'Café']);
        factory(App\Resource::class)->create(['name' => 'Cuadernos']);
        factory(App\Resource::class)->create(['name' => 'Televisor']);
        factory(App\Resource::class)->create(['name' => 'Proyector']);
        factory(App\Resource::class)->create(['name' => 'Laptop']);
    }
}
