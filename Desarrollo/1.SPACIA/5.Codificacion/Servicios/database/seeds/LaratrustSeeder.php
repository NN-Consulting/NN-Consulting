<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class LaratrustSeeder extends Seeder {

    /**
     * Run the database seeds.
     *
     * @return  void
     */
    public function run()
    {
        $this->command->info('Truncating User, Role and Permission tables');
        $this->truncateLaratrustTables();

        $config = config('laratrust_seeder.role_structure');
        $userPermission = config('laratrust_seeder.permission_structure');
        $mapPermission = collect(config('laratrust_seeder.permissions_map'));

        foreach ($config as $key => $modules)
        {

            switch ($key)
            {
                case 'admin':
                    $description = 'The administrator of the platform.';
                    break;

                case 'leader':
                    $description = 'The leader of team(s).';
                    break;

                case 'user':
                    $description = 'A user.';
                    break;

                default:
                    $description = 'A regular user registered in the app.';
                    break;
            }

            // Create a new role
            $role = \App\Role::create([
                'name'         => $key,
                'display_name' => ucwords(str_replace('_', ' ', $key)),
                'description'  => $description
            ]);
            $permissions = [];

            $this->command->info('Creating Role ' . strtoupper($key));

            // Reading role permission modules
            foreach ($modules as $module => $value)
            {

                foreach (explode(',', $value) as $p => $perm)
                {

                    $permissionValue = $mapPermission->get($perm);

                    $permissions[] = \App\Permission::firstOrCreate([
                        'name'         => $permissionValue . '-' . $module,
                        'display_name' => ucfirst($permissionValue) . ' ' . ucfirst($module),
                        'description'  => ucfirst($permissionValue) . ' ' . ucfirst($module),
                    ])->permission_id;

                    $this->command->info('Creating Permission to ' . $permissionValue . ' for ' . $module);
                }
            }

            // Attach all permissions to the role
            $role->permissions()->sync($permissions);

            $this->command->info("Creating '{$key}' user");

//             Create default user for each role
            $user = \App\User::create([
                'first_name' => 'Mesut',
                'last_name'  => 'Özil',
                'email'      => $key . '@nnconsulting.com',
                'password'   => bcrypt('password')
            ]);

            $user->attachRole($role);
        }
    }

    /**
     * Truncates all the laratrust tables and the users table
     *
     * @return    void
     */
    public function truncateLaratrustTables()
    {
        Schema::disableForeignKeyConstraints();
        DB::table('permission_role')->truncate();
        DB::table('permission_user')->truncate();
//        DB::table('role_user')->truncate();
//        \App\Models\User::truncate();
        \App\Role::truncate();
        \App\Permission::truncate();
        Schema::enableForeignKeyConstraints();
    }
}
