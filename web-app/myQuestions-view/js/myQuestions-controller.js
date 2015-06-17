'use strict';

var myQuestionsController = angular.module('myQuestionsController', [])

    .controller('MyQuestionsCtrl', ['$scope',
        function ($scope) {
            /* Miscellaneous functions */
            function intersect(x, y) {
                for (var i = 0; i < x.length; i++) {
                    for (var z = 0; z < y.length; z++) {
                        if (x[i] === y[z]) {
                            return true;
                        }
                    }
                }
                return false;
            }

            //Search controller
            //Data query
            /*$scope.tagSet = [];
            $scope.doubtList = DoubtRequest.queryStartupDoubts();
            $scope.doubtList.$promise.then(function (data) {
                for (var i = data.length - 1; i >= 0; i--) {
                    for (var j = data[i].tags.length - 1; j >= 0; j--) {
                        var tag = data[i].tags[j];
                        if ($scope.tagSet.indexOf(tag) === -1) {
                            $scope.tagSet.push(tag);
                        }
                    }
                }
            });*/


            //Filtering and ordering
            /*$scope.selectedOrder = 0;
            $scope.selectedCriteria = 'title';
            $scope.enteredTags = '';
            $scope.searchTerm = "";

            $scope.tagFilter = function (item) {
                var desiredTags = $scope.enteredTags.split(',').map(function (str) {
                    return str.trim();
                }).filter(function (value) {
                    return value !== ''
                });
                if (desiredTags.length === 0) {
                    return true;
                }
                return intersect(item.tags, desiredTags);
            };*/
        }
    ])
;