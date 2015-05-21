'use strict';

var app = angular.module('app', [
		'ngRoute',
		'dashControllers', 'dashDirectives', 'dashServices', 'dashAnimations', 'userControllers', 'userServices'
	]
);

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/dashboard', {
			templateUrl: 'dashboard-view/partials/dash-view.html',
			controller: 'DashViewCtrl'
		})
		$routeProvider.when('/signup', {
			templateUrl: 'profile-view/partials/createUser.html',
            controller: 'UserViewCtrl'
		})
    $routeProvider.when('/edit', {
        templateUrl: 'profile-view/partials/editUser.html',
        controller: 'UserViewCtrl'
    })
        .otherwise({
			redirectTo: '/'
		});
}]);