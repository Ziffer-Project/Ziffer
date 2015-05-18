'use strict';

var dashServices = angular.module('dashServices', ['ngResource'])

    .factory('CategoryRequest', ['$resource',
        function ($resource) {
            return $resource('dashboard/fetchData/categories');
        }
    ])

    .factory('DoubtRequest', ['$resource',
        function ($resource) {
            return $resource('dashboard/fetchData/questions/:categoryId/:doubtId', {}, {
                queryStartupDoubts: {method: 'GET', params: {categoryId: -1, doubtId: 'doubts'}, isArray: true},
                queryAllDoubts: {method: 'GET', params: {doubtId: 'doubts'}, isArray: true}
            });
        }
    ])

    .factory('SetOffer', ['$resource',
        function ($resource) {
            return $resource('dashboard/mdData/setOffer', {}, {
               setOffer: { method: 'PUT' }
            });
        }
    ]);