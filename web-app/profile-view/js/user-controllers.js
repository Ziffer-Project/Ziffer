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


        $scope.user=profileRequest.queryUser();
        $scope.profile={};
        $scope.profiles=[];

        $scope.signUp=function(profile){
              /**var u=$scope.profile;
              if(typeof u!=='undefined'){
                  var newProfile = {profileId: u.id, name: u.name, email: u.email, aboutMe: u.aboutMe, avatar: u.avatar, phone: u.phone};
                  signUpRequest.signUp(newProfile, {});
              }else {
                  console.log("Please register");
              }**/
            $scope.profiles.push({
                name: profile.name,
                email: profile.email,
                aboutMe:  profile.aboutMe,
                avatar:  profile.avatar,
                phone:  profile.phone
            });
        };

        /***$scope.edit=function(){
                var u=$scope.profile;
                editRequest=queryProfile();
        };***/
        editRequest.edit(info, {},
            function edit(response) {
                var id=$scope.profileId=1;//var u=$scope.profile;
                console.log(response);
            },
            function error(error) {
                console.log('There was an error in communication with the server.');
            }
        );

        $scope.save=function(){
            var u=$scope.profile;
            //editRequest=queryProfile();
        };



        /**.controller('editController', function($scope, $http) {
            $http.get("profile-view/tempData/profile.json")
                .success(function(response) {$scope.profile = response.records;});
        });**/
}]);