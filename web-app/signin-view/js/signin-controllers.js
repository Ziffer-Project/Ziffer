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

.directive('signinBtn', ['$rootScope', 'LoginRequest', function ($rootScope, LoginRequest) {
	return {
		restrict: 'C',
		link: function (scope, elem) {
			elem.bind('click', function (e) {
				var usr = scope.signinUser.username;
				var pwd = scope.signinUser.password;
				if (usr === '' || pwd === '') {}
				else if (usr.replace(/ /g, '').length !== usr.length ||
					  pwd.replace(/ /g, '').length !== pwd.length) {
					// Spaces validation
				} else {
					console.log($rootScope.loggedUser);
					var info = { username: usr, password: pwd };
					LoginRequest.login(info, {},
						function success() {
							console.log('succes');
						},
						function error() {
							console.log('error');
						}
					);
				}
			});
		}
	};
}]);

var signinServices = angular.module('signinServices', ['ngResource'])

.factory('LoginRequest', ['$resource',
	function($resource) {
		return $resource('signin/mkData/signinRequest', {}, {
		login: { method: 'POST'}
	});
}]);