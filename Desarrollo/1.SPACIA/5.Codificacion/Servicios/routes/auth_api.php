<?php

/** AUTH */
Route::post('/logout', 'AuthController@logout');

/** PROFILE */
Route::get('/profile', 'UserController@getProfile');
Route::put('/profile', 'UserController@updateProfile');
Route::post('/profile/update-password', 'UserController@updatePassword');

/** EVENTS */
Route::post('/events', 'EventController@index');