<?php

return [
    'role_structure'       => [
        'admin'  => [
            'users'     => 'c,r,u,d',
            'teams'     => 'c,r,u,d',
            'resources' => 'c,r,u,d',
            'rooms'     => 'c,r,u,d',
            'events'    => 'c,r,u,d',
            'acl'       => 'c,r,u,d',
            'profile'   => 'c,r,u'
        ],
        'leader' => [
            'users'     => 'r',
            'teams'     => 'c,r,u,d',
            'resources' => 'r',
            'rooms'     => 'r',
            'events'    => 'r',
            'acl'       => 'r',
            'profile'   => 'c,r,u',

        ],
        'user'   => [
            'users'     => 'r',
            'teams'     => 'r',
            'resources' => 'r',
            'rooms'     => 'r',
            'events'    => 'r',
            'acl'       => 'r',
            'profile'   => 'c,r,u',
        ],
    ],
    'permission_structure' => [

    ],
    'permissions_map'      => [
        'c' => 'create',
        'r' => 'read',
        'u' => 'update',
        'd' => 'delete'
    ]
];
