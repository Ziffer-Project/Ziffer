'use strict';

var app = angular.module('app', [
        'ngRoute', 'ngStorage',
        'indexControllers', 'indexDirectives',
        'signinControllers', 'signinDirectives', 'signinServices',
        'dashControllers', 'dashDirectives', 'dashServices', 'dashAnimations'
    ])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main-view/main-view.html'
            })
            .when('/signup', {
                templateUrl: 'some/path/to/signup.html'
            })
            .when('/signin', {
                templateUrl: 'signin-view/partials/signin-view.html',
                controller: 'SigninViewCtrl'
            })
            .when('/dashboard', {
                templateUrl: 'dashboard-view/partials/dash-view.html',
                controller: 'DashViewCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    }])

    .run( function ($rootScope, $location, $localStorage) {
        $rootScope.$storage = $localStorage.$default({
            loggedUser: false,
            username: ''
        });
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var path = $location.path();
            if (!$rootScope.$storage.loggedUser) {
                if (path === '/' || path === '' || path === '/signin' || path === '/signup') {
                    // Home needs no security, already going to signin or signup
                } else {
                    $location.path('/signin');
                }
            } else {
                if (path === '/signin' || path === '/signup') $location.path('/dashboard');
            }
        });
    });

// Top bar directives
var indexDirectives = angular.module('indexDirectives', [])

    .directive('topBar', ['$rootScope',
    function ($rootScope) {
        return {
            restrict: 'C',
            link: function (scope, elem) {
                $rootScope.$watch('$storage.loggedUser', function () {
                    var logged = $rootScope.$storage.loggedUser;
                    if (logged) {
                        // Bug when login first with an user, logout and then login again.
                        jQuery('.topBarNotLogged').transition('hide');
                        jQuery('.topBarLogged').transition('show');
                    } else {
                        jQuery('.topBarLogged').transition('hide');
                        jQuery('.topBarNotLogged').transition('show');
                        jQuery('#loggedMenu').dropdown({
                            action: 'hide'
                        });
                    }
                });
            }
        };
    }]);

var indexControllers = angular.module('indexControllers', [])

    .controller('IndexCtrl', ['$scope', '$location', '$rootScope',
    function ($scope, $location, $rootScope) {
        $scope.goDash = function () {
            $location.path('/dashboard');
        };
        $scope.goEdit = function () {
            $location.path('/editProfile');
        };
        $scope.goMyQuestion = function () {
            $location.path('/myQuestions');
        };
        $scope.goMyContributions = function () {
            $location.path('/myContributions');
        };
        $scope.goSignout = function () {
            $rootScope.$storage.loggedUser = false;
            $location.path('/');
        };
    }]);
