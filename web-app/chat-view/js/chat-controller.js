'use strict';

var chatController = angular.module('chatController', [])

        .controller('ChatCtrl', ['$scope', '$rootScope',
            function($scope, $rootScope) {
                $scope.question = $rootScope.$storage.question;
            }
        ])
    ;

var chatDirectives = angular.module('chatDirectives', [])

    .directive('modal', function() {
        return {
            restrict: 'C',
            link: function (scope, elem) {
                jQuery(elem).modal();
            }
        }
    });
