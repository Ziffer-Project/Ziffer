'use strict';

var dashControllers = angular.module('dashControllers', ['angularify.semantic.dropdown'])

.controller('DashViewCtrl', ['$scope', function($scope) {
	$scope.categories = [
		{
			"name": "Algebra",
			"id": 1
		},
		{
			"name": "Geometry",
			"id": 2
		},
		{
			"name": "Calculus",
			"id": 3
		},
		{
			"name": "My doubts",
			"id": -3
		},
		{
			"name": "Contributions",
			"id": -2
		}
	];
	$scope.orderProp = "id";
	$scope.selectedCategory = null;

	$scope.sel = '1';
	$scope.userBox = false;
	$scope.searchTerm = null;
}]);