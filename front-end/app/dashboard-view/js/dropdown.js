'use strict';

angular.module('angularify.semantic.dropdown', [])
    .controller('DropDownController', ['$scope',
        function ($scope) {
            $scope.items = [];

            this.add_item = function (scope) {
                $scope.items.push(scope);
                return $scope.items;
            };

            this.remove_item = function (scope) {
                var index = $scope.items.indexOf(scope);
                if (index !== -1)
                    $scope.items.splice(index, 1);
            };

            this.update_element = function (element) {
                for (var i in $scope.items) {
                    $scope.items[i].element = element;
                }
            };

        }
    ])

.directive('dropdown', function () {
    return {
        restrict: 'E',
        replace: true,
        transclude: true,
        controller: 'DropDownController',
        scope: {
            element: '@',
            open: '@',
            model: '=ngModel'
        },
        template: '<div class="{{ dropdown_class }}">' +
                    '<div class="default text">{{ element.name || "Change category" }}</div>' +
                    '<i class="inverted dropdown icon"></i>' +
                    '<div class="{{ menu_class }}"  ng-transclude>' +
                    '</div>' +
                '</div>',
        link: function (scope, element, attrs, DropDownController) {
            scope.dropdown_class = 'ui selection dropdown';
            scope.menu_class = 'menu transition hidden';
            scope.original_element = scope.element;

            if (scope.open === 'true') {
                scope.is_open = true;
                scope.dropdown_class = scope.dropdown_class + ' active visible';
                scope.menu_class = scope.menu_class + ' visible';
            } else {
                scope.is_open = false;
            }
            DropDownController.add_item(scope);

            /*
             * Watch for element changing
             */
            scope.$watch('element', function (val, oldVal) {
                if (val === undefined || val === oldVal || val === scope.original_element)
                    return;

                scope.model = val;
            });

            /*
             * Watch for ng-model changing
             */
            scope.$watch('model', function (val) {
                // update element or reset the original element if its empty
                scope.model = val;
                DropDownController.update_element(val || scope.original_element);
            });

            /*
             * Click handler
             */
            element.bind('click', function () {
                if (scope.is_open === false) {
                    scope.$apply(function () {
                        scope.dropdown_class = 'ui selection dropdown active visible';
                        scope.menu_class = 'menu transition visible';
                    });
                } else {
                    if (scope.element !== scope.original_element)
                        scope.model = scope.element;
                    scope.$apply(function () {
                        scope.dropdown_class = 'ui selection dropdown';
                        scope.menu_class = 'menu transition hidden';
                    });
                }
                scope.is_open =! scope.is_open;
            });
        }
    };
})

.directive('dropdownGroup', function () {
    return {
        restrict: 'AE',
        replace: true,
        transclude: true,
        require: '^dropdown',
        scope: {
            element: '=element'
        },
        template: '<div class="item" ng-transclude >{{ item_element }}</div>',
        link: function (scope, element, attrs, DropDownController) {

            // Check if element= was set... if not take the contents of the dropdown-group tag
            // element= is for dynamic variables from something like ng-repeat {{variable}}
            if (scope.element === undefined) {
                scope.item_element = element.children()[0].innerHTML;
            } else {
                scope.item_element = scope.element;
            }

            //
            // Menu item click handler
            //
            element.bind('click', function () {
                DropDownController.update_element(scope.item_element);
            });
        }
    };
});
