/**
 * Created by Familia López Ochoa on 19/05/2015.
 */
'use strict';

var userServices = angular.module('userServices', ['ngResource'])

    .factory('profileRequest', ['$resource',
        function ($resource) {
            return $resource('signup/fetchData/edit');
        }
    ])

    .factory('editRequest', ['$resource',
        function ($resource) {
            return $resource('signup/fetchData/profile/:userId/:profileId', {}, {
                queryUser: {method: 'GET', params: {userId: 'name'}, isArray: true},
                queryProfile: {method: 'GET', params: {profileId: 'name'}, isArray: true}
            });
        }
    ])

    .factory('edit', ['$resource',
        function ($resource) {
            return $resource('signup/mdData/edit', {}, {
                edit: { method: 'PUT' }
            });
        }
    ]);