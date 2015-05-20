'use strict';

var app = angular.module('app', [
		'ngRoute',
		'dashControllers', 'dashDirectives', 'dashServices', 'dashAnimations', 'userControllers'
	]
);

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/dashboard', {
			templateUrl: 'dashboard-view/partials/dash-view.html',
			controller: 'DashViewCtrl'
		})
		.when('/signup', {
			templateUrl: 'profile-view/partials/createUser.html',
            controller: 'UserViewCtrl'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);