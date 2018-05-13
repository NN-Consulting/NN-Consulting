<?php

use Faker\Generator as Faker;

$factory->define(App\Event::class, function (Faker $faker) {

    $beginnings = ['08:00', '10:00', '12:00', '14:00', '16:00', '18:00'];
    $ends = ['10:00', '12:00', '14:00', '16:00', '18:00', '20:00'];
    $digit = $faker->numberBetween(0, 5);

    return [
        'room_id'   => $faker->numberBetween(1, 6),
        'name'      => substr($sentence = $faker->sentence($faker->numberBetween(2, 4)), 0, strlen($sentence) - 1),
        'hour_from' => $beginnings[$digit],
        'hour_to'   => $ends[$digit],
        'date'      => now()->addDays($faker->numberBetween(0, 20)),
    ];
});
