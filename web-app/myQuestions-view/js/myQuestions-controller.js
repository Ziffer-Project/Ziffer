'use strict';

var myQuestionsController = angular.module('myQuestionsController', [])

    .controller('MyQuestionsCtrl', ['$scope', '$rootScope', 'UserQuestionsRequest', '$location',
        function ($scope, $rootScope, UserQuestionsRequest, $location) {
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
            $scope.tagSet = [];
            $scope.doubtList = UserQuestionsRequest.getQuestions({username: $rootScope.$storage.username},
                function success(data) {
                    for (var i = data.length - 1; i >= 0; i--) {
                        for (var j = data[i].tags.length - 1; j >= 0; j--) {
                            var tag = data[i].tags[j];
                            if ($scope.tagSet.indexOf(tag) === -1) {
                                $scope.tagSet.push(tag);
                            }
                        }
                    }
                },
                function error(err) {}
            );


            //Filtering and ordering
            $scope.selectedOrder = 0;
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
            };

            $scope.extDoubt = {};
            $scope.chatInfo = {};

            $scope.goToClassroom = function() {
                $location.path('/classroom');
            };
        }
    ])
;

var myQuestionsDirectives = angular.module('myQuestionsDirectives', [])

    .directive('modal', function() {
        return {
            restrict: 'C',
            link: function (scope, elem) {
                jQuery(elem).modal();
            }
        }
    })

    .directive('peek', ['$timeout', function($timeout) {
        return {
            restrict: 'C',
            link: function(scope, elem) {
                elem.bind('click', function() {
                    scope.$parent.extDoubt = scope.doubt;
                    scope.$parent.extDoubt.description = scope.doubt.description;
                    $timeout(function() {
                        $('.full-question-modal').modal('show');
                    }, 200);
                });
            }
        };
    }])

    .directive('acceptOfferBtn', ['$rootScope', function($rootScope) {
        return {
            restrict: 'C',
            link: function(scope, elem) {
                elem.bind('click', function() {
                    var question = scope.extDoubt;
                    delete question.offers;
                    question.offer = scope.offer;
                    $rootScope.$storage.question = question;
                });
            }
        }
    }])

;

var myQuestionsServices = angular.module('myQuestionsServices', ['ngResource'])

    .factory('UserQuestionsRequest', ['$resource',
        function ($resource) {
            return $resource('myQuestions/fetchData/userQuestions', {}, {
                    getQuestions: { method: 'GET', isArray: true }
                }
            );
        }
    ])

;