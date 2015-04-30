'use strict';

var dashApp = angular.module('dashApp', [
	'ngRoute', 'dashDirectives', 'dashControllers'
	]
);

dashApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/dashboard', {
		templateUrl: 'dashboard-view/partials/dash-view.html',
		controller: 'DashViewCtrl'
	}).
	otherwise({
		redirectTo: '/dashboard'
	});
}]);