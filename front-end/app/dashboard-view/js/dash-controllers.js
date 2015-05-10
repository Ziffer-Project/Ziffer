'use strict';

var dashControllers = angular.module('dashControllers', [])

.controller('DashViewCtrl', ['$scope', 'CategoryRequest', 'DoubtRequest',
	function($scope, CategoryRequest, DoubtRequest) {
		/* Miscellaneous functions */
		function intersect(x, y){
			var ret = [];
			for (var i = 0; i < x.length; i++) {
				for (var z = 0; z < y.length; z++) {
					if (x[i] === y[z]) {
						return true;
					}
				}
			}
			return false;
		}


		//Category banner controller
		$scope.categories = CategoryRequest.query();
		$scope.orderCategory = "id";
		$scope.selectedCategory = null;

		//Search controller
		//Data query
		$scope.tagSet = [];
		$scope.doubtList = DoubtRequest.query();
		$scope.doubtList.$promise.then(function (data) {
			for (var i = data.length - 1; i >= 0; i--) {
				for (var j = data[i].tags.length - 1; j >= 0; j--) {
					var tag = data[i].tags[j];
					if ($scope.tagSet.indexOf(tag) === -1) { $scope.tagSet.push(tag); }
				}
			}
		});


		//Filtering and ordering
		$scope.selectedOrder = 0;
		$scope.selectedCriteria = 'title';
		$scope.enteredTags = '';
		$scope.searchTerm = "";

		$scope.tagFilter = function(item) {
			var desiredTags = $scope.enteredTags.split(',').map(function (str) {
				return str.trim();
			}).filter(function (value) { return value !== '' });
			if (desiredTags.length === 0) { return true; }
			if (intersect(item.tags, desiredTags)) { return true; }
			return false;
		};
	}
]);