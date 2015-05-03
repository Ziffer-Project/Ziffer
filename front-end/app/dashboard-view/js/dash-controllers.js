'use strict';

var dashControllers = angular.module('dashControllers', ['angularify.semantic.dropdown'])

.controller('DashViewCtrl', ['$scope', function($scope) {
	//Category banner controller
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

	$scope.doubtList = [
		{
			'title': 'How much is 2+2?',
			'dateCreated': '20150215',
			'closeDate': '20150615',
			'tags': ['math', 'arithmetic'],
			'user': {
				'username': 'user1',
				'score': 0
			}
		},
		{
			'title': 'How much is 3+3?',
			'dateCreated': '20150115',
			'closeDate': '20150515',
			'tags': ['math'],
			'user': {
				'username': 'user2',
				'score': 2
			}
		},
		{
			'title': 'How much is 4+4?',
			'dateCreated': '20140215',
			'closeDate': '20170615',
			'tags': ['adding'],
			'user': {
				'username': 'user3',
				'score': 5
			}
		},
		{
			'title': 'How much is 6+10?',
			'dateCreated': '20150926',
			'closeDate': '20151115',
			'tags': ['substraction', 'operation'],
			'user': {
				'username': 'user2',
				'score': 1000
			}
		},
		{
			'title': 'How much is 2+2?',
			'dateCreated': '20130115',
			'closeDate': '20140605',
			'tags': ['just asking'],
			'user': {
				'username': 'user5',
				'score': 351
			}
		},
		{
			'title': 'How much is 2+2?',
			'dateCreated': '20150201',
			'closeDate': '20150601',
			'tags': ['politics'],
			'user': {
				'username': 'user6',
				'score': 96
			}
		}
	];
}]);