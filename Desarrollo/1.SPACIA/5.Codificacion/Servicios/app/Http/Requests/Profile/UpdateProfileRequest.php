<?php

namespace App\Http\Requests\Profile;

use Illuminate\Foundation\Http\FormRequest;

class UpdateProfileRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return auth()->user()->can('update-profile');
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'first_name'   => 'string|min:2|max:100',
            'last_name'    => 'string|min:2|max:100',
            'email'        => 'email|unique:users',
            'birthday'     => 'string',
            'phone_number' => 'string|numeric|digits_between:7,9',
        ];
    }
}
