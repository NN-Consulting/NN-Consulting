<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Team
 *
 * @package App
 */
class Team extends Model {

    /**
     * @var string
     */
    protected $primaryKey = 'team_id';

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
    public function members()
    {
        return $this->hasMany(User::class, 'team_id', 'team_id');
    }
}
