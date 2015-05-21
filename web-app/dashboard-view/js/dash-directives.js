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
                link: function(scope, elem) {
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
                    $(elem).modal("setting", {
                        onApprove: function () {
                            /*if (typeof val !== 'undefined' && val.length > 0) {
                             return scope.extDoubt.offerAmount.match(/^\d{0,9}$/);
                             } else {
                             return false;
                             }*/ return false;
                        }
                    });
                    scope.$watch('extDoubt.offerAmount', function(val) {
                        if (typeof val !== 'undefined' && val.length > 0) {
                            console.log(val.match(/^\d{0,9}$/));
                        }
                    });
                }
            };
        })

        .directive('submitOfferBtn', function () {
            return {
                restrict: 'C',
                link: function (scope, elem) {
                    $(elem).click(function () {
                        var val = scope.extDoubt.offerAmount;
                        if (typeof val !== 'undefined' && val.length > 0 && val.match(/^\d{0,9}$/)) {
                            $('.modal').modal('toggle');
                        }
                    });
                }
            };
        })

        .directive('peek', ['$timeout', 'DoubtRequest', function($timeout, DoubtRequest) {
            return {
                restrict: 'C',
                link: function(scope, elem, attrs) {
                    $(elem).click(function() {
                        scope.$parent.extDoubt = scope.doubt;
                        var desiredId = scope.doubt.id;
                        var catId = scope.doubt.categoryId;
                        /* Commented out for future use, thi fetches the description of a particular
                         question from the server.
                         var el = DoubtRequest.get({categoryId: catId, doubtId: desiredId}, function(el) {
                         scope.$parent.extDoubt.description = el.description;
                         });*/
                        scope.$parent.extDoubt.description = scope.doubt.description;
                        $timeout(function() {
                            $('.modal').modal('show');
                        }, 200);
                    });
                }
            };
        }])
    ;