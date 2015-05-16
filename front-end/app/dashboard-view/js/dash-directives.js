'use strict';

var dashDirectives = angular.module('dashDirectives', [])

.directive('dropdown', function() {
	return {
		restrict: 'C',
		link: function(scope, elem, attrs) {
			$(elem).dropdown({
				on: 'hover',
				transition: 'scale'
			});
		}
	};
})

//Note categoryInfo camelcase, in the HTML file it's category-info
.directive('categoryInfo', ['$timeout', function($timeout) {
	return {
		restrict: 'C',
		link: function(scope, elem, attrs) {
			$timeout(function() {
				$(elem).transition('fade');
			}, 500);
		}
	};
}])

.directive('modal', function() {
	return {
		restrict: 'C',
		link: function(scope, elem, attrs) {
			$(elem).modal();
		}
	};
})

.directive('peek', ['$timeout', 'CategoryRequest', function($timeout, CategoryRequest) {
	return {
		restrict: 'C',
		link: function(scope, elem, attrs) {
			$(elem).click(function() {
				scope.$parent.extDoubt = scope.doubt;
				var desiredId = scope.doubt.questionId;
				var el = CategoryRequest.get({categoryId:desiredId}, function(el) {
					scope.$parent.extDoubt.description = el.description;
				});
				$timeout(function() {
					$('.modal').modal('show');
				}, 150);
			});
		}
	};
}])

.directive('offerInput', function() {
	return {
		restrict: 'C',
		link: function(scope, elem, attrs) {
			scope.$watch('extDoubt.offerAmount', function(val) {
				if (typeof val !== 'undefined' && val.length > 0) {
					console.log(val.match(/^\d{0,9}$/));
				}
			});
		}
	};
});