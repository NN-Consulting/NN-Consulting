<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Room
 *
 * @package App
 */
class Room extends Model {

    /**
     * @var string
     */
    protected $primaryKey = 'room_id';

    /**
     * @var array
     */
    protected $guarded = [];

    /**
     * @var bool
     */
    public $timestamps = false;

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function events()
    {
        return $this->hasMany(Event::class, 'room_id', 'room_id');
    }
}
