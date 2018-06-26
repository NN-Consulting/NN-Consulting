<?php
/**
 * Created by Kenny Horna.
 * Date: 21/02/18
 * Time: 11:50 AM
 */

namespace App\Repositories;

use App\Services\ImageService;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Container\Container as App;
use ReflectionClass;

/**
 * Class Repository
 *
 */
abstract class Repository  {

    /**
     * @var App
     */
    private $app;

    /**
     * @var
     */
    protected $model;

    /**
     * @param App $app
     */
    public function __construct(App $app) {
        $this->app = $app;
        $this->makeModel();
    }

    /**
     * Specify Model class name
     *
     * @return mixed
     */
    abstract function model();

    /**
     * @param array $columns
     * @return mixed
     */
    public function all($columns = array('*')) {
        return $this->model->get($columns);
    }

    /**
     * @param int $perPage
     * @param array $columns
     * @return mixed
     */
    public function paginate($perPage = 10, $columns = array('*')) {
        return $this->model->paginate($perPage, $columns);
    }

    /**
     * @param array $data
     * @return mixed
     */
    public function create(array $data) {
        return $this->model->create($data);
    }

    /**
     * @param array $data
     * @param $model
     * @param string $attribute
     * @return mixed
     */
    public function update(array $data, $model, $attribute = null) {

        if ($model instanceof  Model)
        {
            return $model->update($data);
        }
        else
        {
            if (is_null($attribute)) $attribute = $this->model->getKeyName();
            return $this->model->where($attribute, '=', $model)->update($data);
        }
    }

    /**
     * @param $model
     * @return mixed
     * @internal param $id
     */
    public function delete($model) {
        return $model instanceof Model
            ? $model->delete()
            : $this->model->destroy($model);
    }

    /**
     * @param $id
     * @param array $columns
     * @return mixed
     */
    public function find($id, $columns = array('*')) {
        return $this->model->find($id, $columns);
    }

    /**
     * @param $attribute
     * @param $value
     * @param array $columns
     * @return mixed
     */
    public function findBy($attribute, $value, $columns = array('*')) {
        return $this->model->where($attribute, '=', $value)->first($columns);
    }


    /**
     * @return Model
     */
    public function makeModel() {
        $model = $this->app->make($this->model());

        return $this->model = $model;
    }

    /**
     * @param $attribute
     * @return string
     */
    public function getPrimaryKeyOfModel($attribute): string
    {
            $reflection = new ReflectionClass(get_class($this->model));
            $attribute = $reflection->getShortName();

        return $attribute;
    }

    public function updateImage($image, $extension, $user)
    {
        if($user->image != "" && $user->image != 'images/users/avatars/default.jpg') {
            \File::delete(storage_path() . '/app/public/' . $user->image);
        }

        $directory = storage_path() . '/app/public/images/users/avatars';
        $fileName = ImageService::store($image, $directory, $extension, false, $user->user_id);
        $user->image = 'images/users/avatars/' . $fileName;

        $user->save();
    }
}