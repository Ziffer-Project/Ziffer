'use strict';

var app = angular.module('app', [
	'ngRoute',
	'dashControllers', 'dashDirectives', 'dashServices', 'dashAnimations'
	]
);

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
	.when('/dashboard', {
		templateUrl: 'dashboard-view/partials/dash-view.html',
		controller: 'DashViewCtrl'
	})
	.when('/signup', {
		templateUrl: '<p style="margin-top: 50px">YEAH</p>'
	})
	.otherwise({
		redirectTo: '/'
	});
}]);