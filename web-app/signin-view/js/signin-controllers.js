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
                    var errMsg = jQuery('.login-error-message');
                    if (usr === '' || pwd === '') {
                        if (!errMsg.transition('is visible')) errMsg.transition('fade');
                    }
                    else if (usr.replace(/ /g, '').length !== usr.length ||
                        pwd.replace(/ /g, '').length !== pwd.length) {
                        // Spaces validation
                        if (!errMsg.transition('is visible')) errMsg.transition('fade');
                    } else {
                        var info = {username: usr, password: pwd};
                        LoginRequest.login(info, {},
                            function success(response) {
                                $rootScope.$storage.loggedUser = response.access;
                                $rootScope.$storage.username = usr;
                                // Bug when login first with an user, logout and then login again.
                                if (!response.access) {
                                    if (!errMsg.transition('is visible')) errMsg.transition('fade');
                                } else {
                                    if (errMsg.transition('is visible')) errMsg.transition('fade');
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