'use strict';

// Controller
var signinControllers = angular.module('signinControllers', [])

    .controller('SigninViewCtrl', ['$scope', '$location',
        function ($scope, $location) {
            $scope.signinUser = {};
            $scope.signinUser.username = "";
            $scope.signinUser.password = "";
            $scope.goHome = function () {
                $location.path('/');
            };
        }
    ]);


// Directives
var signinDirectives = angular.module('signinDirectives', [])

    .directive('signinBtn', ['LoginAction',
        function (LoginAction) {
            return {
                restrict: 'C',
                link: function (scope, elem) {
                    elem.bind('click', function (e) {
                        LoginAction.executeLogin(scope.signinUser.username, scope.signinUser.password);
                    });
                }
            };
        }
    ])

    .directive('inputLogin', ['LoginAction',
        function (LoginAction) {
            return {
                restrict: 'C',
                link: function (scope, elem) {
                    elem.bind('keypress', function (event) {
                        if (event.which === 13) {
                            LoginAction.executeLogin(scope.signinUser.username, scope.signinUser.password);
                        }
                    });
                }
            };
        }
    ]);

// Services
var signinServices = angular.module('signinServices', ['ngResource'])

    .factory('LoginRequest', ['$resource',
        function($resource) {
            return $resource('signin/mkData/signinRequest', {}, {
                login: { method: 'POST'}
            });
        }])

    .factory('LoginAction', ['$rootScope', '$location', 'LoginRequest',
        function ($rootScope, $location, LoginRequest) {
            return {
                executeLogin: function (usr, pwd) {
                    if (usr === '' || pwd === '') {
                    }
                    else if (usr.replace(/ /g, '').length !== usr.length ||
                        pwd.replace(/ /g, '').length !== pwd.length) {
                        // Spaces validation
                    } else {
                        var info = {username: usr, password: pwd};
                        LoginRequest.login(info, {},
                            function success(response) {
                                $rootScope.loggedUser = response.access;
                                var ele = $('.login-error-message');
                                if (!response.access) {
                                    if (!ele.transition('is visible')) ele.transition('fade');
                                } else {
                                    if (ele.transition('is visible')) ele.transition('fade');
                                    $rootScope.user = usr;
                                    $location.path('/dashboard');
                                }
                            },
                            function error(error) {
                                console.log('There was an error in communication with the server.');
                            }
                        );
                    }
                }
            };
        }
    ]);