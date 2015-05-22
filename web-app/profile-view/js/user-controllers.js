/**
 * Created by Familia López Ochoa on 22/05/2015.
 */
'use strict'

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

    .factory('SignUpAction', ['$rootScope','$location','SignUpRequest',
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

    ])

    .factory('editRequest', ['$resource',
        function($resource){
            return $resource('/signup/fetchData/profiles:/profileId',{},{
                queryProfile: {method:'GET', params:{profileId:1}, isArray:false}
            });
        }
    ]);