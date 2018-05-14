<?php

use Faker\Generator as Faker;


$factory->define(App\Resource::class, function (Faker $faker) {
    return [
        'name' => $faker->city,
    ];
});
