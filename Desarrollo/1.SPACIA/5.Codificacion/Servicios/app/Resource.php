<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Resource
 *
 * @package App
 */
class Resource extends Model
{

    /**
     * @var string
     */
    protected $primaryKey = 'resource_id';

    /**
     * @var array
     */
    protected $guarded = [];

    /**
     * @var bool
     */
    public $timestamps = false;

}
