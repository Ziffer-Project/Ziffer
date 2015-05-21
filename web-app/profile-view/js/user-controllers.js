'use strict';

var userControllers = angular.module('userControllers', [])

.controller('UserViewCtrl', ['$scope', '$http', 'profileRequest', 'editRequest', 'signUpRequest', function($scope, $http, profileRequest, editRequest, signUpRequest) {
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
        /***var request = $http({
            method: "post",
            url: "/profile-view/partials/createUser.html",
            //transformRequest: transformRequestAsFormPost,
            params: {
                nombre: $scope.nombre,
                username: $scope.username,
                password: $scope.password
            }
        });***/

        $scope.user=profileRequest.queryUser();
        $scope.profile={};

        $scope.signUp=function(){
              var u=$scope.profile;
              if(typeof u!=='undefined'){
                  var newProfile = {profileId: u.id, name: u.name, email: u.email, aboutMe: u.aboutMe, avatar: u.avatar, phone: u.phone};
                  signUpRequest.signUp(newProfile, {});
              }else{
                  console.log("Please register");
              }
        };

        $scope.edit=function(){
                var u=$scope.profile;
                editRequest=queryProfile();
        };

        $scope.save=function(){
            var u=$scope.profile;
            //editRequest=queryProfile();
        };
        /**$scope.update=function($scope.username){
            //$scope.user=copy($scope.user);
            $http({
                url: '/profile-view/js/createUser.html',
                method: "GET",
                params: {username: User.username}
            });
        }*/

        /**.controller('editController', function($scope, $http) {
            $http.get("profile-view/tempData/profile.json")
                .success(function(response) {$scope.profile = response.records;});
        });**/
}]);