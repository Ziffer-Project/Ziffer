'use strict';

var dashServices = angular.module('dashServices', ['ngResource']);

dashServices.factory('CategoryRequest', ['$resource',
	function($resource) {
		return $resource('dashboard-view/tempData/categories.json');
}])

.factory('DoubtRequest', ['$resource',
	function($resource) {
	return $resource('dashboard-view/tempData/doubts.json');
}]);