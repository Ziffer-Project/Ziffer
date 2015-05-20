'use strict';

var userControllers = angular.module('userControllers', [])

.controller('UserViewCtrl', ['$scope', '$http', 'profileRequest', function($scope, $http) {
    /*$scope.create=function($scope){
        $scope.user= {
            params: {
                'name': $scope.name,
                'rol': scope.rol,
                'username': $scope.username,
                'password': $scope.password,
                'confirm': $scope.confirm
            }
        };
    }*/
        var request = $http({
            method: "post",
            url: "/profile-view/partials/createUser.html",
            //transformRequest: transformRequestAsFormPost,
            params: {
                nombre: $scope.nombre,
                username: $scope.username,
                password: $scope.password
            }
        });

        $scope.user=profileRequest.queryUser();

        /**$scope.update=function($scope.username){
            //$scope.user=copy($scope.user);
            $http({
                url: '/profile-view/js/createUser.html',
                method: "GET",
                params: {username: User.username}
            });
        }*/

        .controller('editController', function($scope, $http) {
            $http.get("profile-view/tempData/users.json")
                .success(function(response) {$scope.users = response.records;});
        });
}]);