'use strict';

var dashServices = angular.module('dashServices', ['ngResource'])

.factory('CategoryRequest', ['$resource',
	function($resource) {
		return $resource('dashboard-view/tempData/categories/:categoryId.json', {}, {
			query: {method:'GET', params:{categoryId:'categories'}, isArray:true}
		});
}])

.factory('DoubtRequest', ['$resource',
	function($resource) {
	return $resource('dashboard-view/tempData/doubts.json');
}]);