'use strict';

var dashDirectives = angular.module('dashDirectives', []);

dashDirectives.directive('categoryAnimation', function() {

	var linkFn;
	linkFn = function(scope, element, attrs) {
		angular.element('.categoy-info').transition('fade');
	}
	// Runs during compile
	return {
		restrict: 'C',
		link: linkFn
	};
});