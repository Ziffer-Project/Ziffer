'use strict';

var dashControllers = angular.module('dashControllers', [])

.controller('DashViewCtrl', ['$scope', 'CategoryRequest', 'DoubtRequest',
	function($scope, CategoryRequest, DoubtRequest) {
		//Category banner controller
		$scope.categories = CategoryRequest.query();
		$scope.orderCategory = "id";
		$scope.selectedCategory = null;

		//Search controller
		$scope.selectedOrder = false;
		$scope.selectedCriteria = null;
		$scope.filterByUser = false;
		$scope.tagLabels = [
			{
				'id': 1,
				'name': 'Tag 1',
				'included': false
			}
		];
		$scope.searchTerm = "";

		$scope.doubtList = DoubtRequest.query();
	}
]);