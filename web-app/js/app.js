'use strict';

var app = angular.module('app', [
        'ngRoute', 'ngStorage',
        'indexControllers',
        'signinControllers', 'signinDirectives', 'signinServices',
        'dashControllers', 'dashDirectives', 'dashServices', 'dashAnimations',
        'myQuestionsController', 'myQuestionsServices'
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
            .when('/myQuestions', {
                templateUrl: 'myQuestions-view/partials/myQuestions-view.html',
                controller: 'MyQuestionsCtrl'
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
            jQuery('#main-body').nextAll('div').remove();
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

var indexControllers = angular.module('indexControllers', [])

    .controller('IndexCtrl', ['$scope', '$location', '$rootScope', 'LogoutRequest',
    function ($scope, $location, $rootScope, LogoutRequest) {
        $scope.goDash = function () {
            $location.path('/dashboard');
        };
        $scope.goEdit = function () {
            $location.path('/editProfile');
        };
        $scope.goMyQuestions = function () {
            $location.path('/myQuestions');
        };
        $scope.goMyContributions = function () {
            $location.path('/myContributions');
        };
        $scope.goSignout = function () {
            LogoutRequest.logout();
            $rootScope.$storage.loggedUser = false;
            $rootScope.$storage.username = '';
            $location.path('/');
        };
    }]);
