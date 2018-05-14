<?php

use Faker\Generator as Faker;

$factory->define(App\Room::class, function (Faker $faker) {
    return [
        'name'     => $faker->colorName,
        'capacity' => $faker->numberBetween(4, 20),
    ];
});
