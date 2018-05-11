<?php
/**
 * Created by Kenny Horna
 * Date: 20/10/17
 * Time: 04:35 PM
 */

namespace App\Repositories;

use Illuminate\Database\Eloquent\Builder;

class UserRepository extends Repository {

    /**
     * Specify Model class name
     *
     * @return mixed
     */
    function model()
    {
        return 'App\User';
    }

    /**
     *
     *
     * @param $query
     * @return mixed
     */
    public function search($query): Builder
    {
        $model = $this->model
            ->orWhere('first_name', 'LIKE', "%$query%")
            ->orWhere('last_name', 'LIKE', "%$query%")
            ->orWhere('email', 'LIKE', "%$query%");

        return $model;
    }
}