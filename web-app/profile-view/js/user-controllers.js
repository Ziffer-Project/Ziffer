'use stritc'

var userControllers =angular.module()
    .controller('signUpCtrl',['$scope','$location',
        function($scope, $location){
            $scope.user={};
            $scope.user.username="";
            $scope.user.password="";
            $scope.user.confirm="";
        }
    ]);


var userServices = angular.module('userServices', ['ngResource'])

    .factory('signUpRequest', ['$resource',
        function($resource){
            return $resource('signup/mkData/signUp', {}, {
                signUp: {method: 'POST'}
            });
        }
    ])

    .factory('SgnUpAction', ['$rootScope','$location','SignUpRequest',
        function($rootScope, $location, SignUpRequest){
            return{
                doSignUp: function(username, password){
                    var info={username: username, password: password};
                    SignUpRequest.signUp(info,{},
                        function success(response){
                            $rootScope.usr=username;
                            $rootScope.pwd=password;
                        }
                    )
                }
            }
        }

    ]);