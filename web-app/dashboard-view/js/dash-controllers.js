'use strict';

var dashControllers = angular.module('dashControllers', [])

    .controller('DashViewCtrl', ['$scope', 'CategoryRequest', 'DoubtRequest', 'SetOffer',
        function ($scope, CategoryRequest, DoubtRequest, SetOffer) {
            /* Miscellaneous functions */
            function intersect(x, y) {
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

            $scope.$watch('selectedCategory', function () {
                if ($scope.selectedCategory) {
                    var id = $scope.selectedCategory.id;
                    DoubtRequest.queryAllDoubts({categoryId: id}, function (response) {
                        console.log(response);
                    },
                    function (errResponse) {
                        console.log(errResponse);
                    });
                }
            });

            //Search controller
            //Data query
            $scope.tagSet = [];
            $scope.doubtList = DoubtRequest.queryStartupDoubts();
            $scope.doubtList.$promise.then(function (data) {
                for (var i = data.length - 1; i >= 0; i--) {
                    for (var j = data[i].tags.length - 1; j >= 0; j--) {
                        var tag = data[i].tags[j];
                        if ($scope.tagSet.indexOf(tag) === -1) {
                            $scope.tagSet.push(tag);
                        }
                    }
                }
            });


            //Filtering and ordering
            $scope.selectedOrder = 0;
            $scope.selectedCriteria = 'title';
            $scope.enteredTags = '';
            $scope.searchTerm = "";

            $scope.tagFilter = function (item) {
                var desiredTags = $scope.enteredTags.split(',').map(function (str) {
                    return str.trim();
                }).filter(function (value) {
                    return value !== ''
                });
                if (desiredTags.length === 0) {
                    return true;
                }
                return intersect(item.tags, desiredTags);
            };

            $scope.extDoubt = {};
            $scope.extDoubt.offerAmount = 0;

            //Fix submit offer
            $scope.submitOffer = function () {
                var q = $scope.extDoubt;
                if (typeof q.amount !== 'undefined' && q.amount.length > 0 && q.amount.match(/^\d{0,9}$/)) {
                    console.log($scope.extDoubt.offerAmount); // erase
                    var info = {categoryId: q.categoryId, doubtId: q.id, amount: q.amount};
                    SetOffer.setOffer(info, {});
                } else {
                    console.log("invalid offer");
                }
            };
        }
    ]);