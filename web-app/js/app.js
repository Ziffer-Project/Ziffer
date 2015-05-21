'use strict';

var app = angular.module('app', [
        'ngRoute',
        'indexControllers', 'indexDirectives',
        'signinControllers', 'signinDirectives', 'signinServices',
        'dashControllers', 'dashDirectives', 'dashServices', 'dashAnimations'
    ]
)

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

    .run( function ($rootScope, $location) {
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            console.log($rootScope.loggedUser);
            if (!$rootScope.loggedUser) {
                var path = $location.path();
                if (path === '/' || path === '' || path === '/signin' || path === '/signup') {
                    // Home needs no security, already going to signin or signup
                } else {
                    $location.path('/signin');
                }
            }
        });
    });

// Top ba directives
var indexDirectives = angular.module('indexDirectives', [])

    .directive('topBar', ['$rootScope',
    function ($rootScope) {
        return {
            restrict: 'C',
            link: function (scope, elem) {
                $rootScope.$watch('loggedUser', function () {
                    var logged = $rootScope.loggedUser;
                    if (logged) {
                        $('.topBarNotLogged').transition('hide');
                        $('.topBarLogged').transition('show');
                    } else {
                        $('.topBarLogged').transition('hide');
                        $('.topBarNotLogged').transition('show');
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
            $rootScope.loggedUser = false;
            $location.path('/');

        };
        $scope.username = $rootScope.username;
    }]);
