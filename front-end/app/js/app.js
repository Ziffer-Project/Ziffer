'use strict';

var app = angular.module('app', [
	'ngRoute',
	'signinControllers', 'signinDirectives', 'signinServices',
	'dashControllers', 'dashDirectives', 'dashServices', 'dashAnimations'
	]
)

.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'home-view/partials/home-view.html'
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
		if ($rootScope.loggedUser == null) {
			var path = $location.path();
			if (path === '/' || path === '' || path === '/signin' || path === '/signup') {
				// Home needs no security, already going to signin or signup
				console.log(path);
			} else {
				$location.path('/signin');
			}
		}
	});
});