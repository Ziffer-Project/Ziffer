/**
 * Created by Familia López Ochoa on 15/05/2015.
 */
var app = angular.module("testApp", ['']);

app.controller('redirect', ['$scope', function ($scope) {
    $scope.myMethod = function () {

    }
}]);

app.controller('TestCtrl2', ['$scope', 'TestCtrl1', function ($scope, TestCtrl1) {
    redirect.myMethod();
}]);