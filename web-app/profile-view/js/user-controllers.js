/**
 * Created by Familia López Ochoa on 22/05/2015.
 */
'use strict'

var userControllers =angular.module('signUpControllers',[])
    .controller('signUpCtrl',['$scope','$location',
        function($scope, $location){
            $scope.profile={};
            $scope.profile.username="";
            $scope.profile.password="";
            $scope.profile.confirm="";
            //$scope.home=function(){$location.path('/');}
        }
    ]);


var userServices = angular.module('signUpServices', ['ngResource'])

    .factory('signUpRequest', ['$resource',
        function($resource){
            return $resource('signup/mkData/signUp', {}, {
                signUp: {method: 'POST'}
            });
        }
    ])

    .factory('SignUpAction', ['$rootScope','$location','signUpRequest',
        function($rootScope, $location, signUpRequest){
            return{
                signUp: function(username, password){
                    var info={username: username, password: password};
                    signUpRequest.signUp(info,{},
                        function success(){
                            $rootScope.usr=username;
                            $rootScope.pwd=password;
                            $location.path('/dashboard');
                        }
                    )
                }
            }
        }

    ])

    .factory('editRequest', ['$resource',
        function($resource){
            return $resource('/signup/fetchData/profiles:/profileId',{},{
                queryProfile: {method:'GET', params:{profileId:'profileId'}, isArray:false}
            });
        }
    ]);


    var signUpDirectives = angular.module('signUpDirectives',[])
        .directive('register',['signUpAction',
            function(signUpAction){
            return {
                restrict: 'C',
                link: function(scope,elem) {
                    elem.bind('click', function (e) {
                        signUpAction.signUp(scope.profile.username, scope.profile.password);
                    });
                }
            };
            }
        ]);