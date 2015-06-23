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
            $scope.goHome = function () {
                $location.path('/');
            };
        }
    ]);


var signUpServices = angular.module('signUpServices', ['ngResource'])

    .factory('signUpRequest', ['$resource',
        function($resource){
            return $resource('signup/mkData/signUp', {}, {
                signUp: {method: 'POST'}
            });
        }
    ])

    .factory('signUpAction', ['$rootScope','$location','signUpRequest',
        function($rootScope, $location, signUpRequest){
            return{
                signUp: function(username, password,confirm){
                    var info={username: username, password: password,confirm: confirm};
                    if(password!=confirm){
                        alert("Las contraseñas no coinciden");
                    }else{
                        signUpRequest.signUp(info,{},
                            function success(){
                                $rootScope.username=username;
                                $rootScope.password=password;
                                $location.path('/dashboard');
                            }
                        )
                    }
                }
            }
        }

    ])

    .factory('editRequest', ['$resource',
        function($resource){
            return $resource('/signup/fetchData/profiles/:profileId',{},{
                queryProfile: {method:'GET', params:{profileId:'profileId'}, isArray:false}
            });
        }
    ])

    .factory('recoverPass', ['$resource',
        function($resource){
            return $resource('/signup/fetchData/recoverPass',{},{
                recover:{method: 'GET', params:{profileId:'profileId'}, isArray:false}
            });
        }
    ])

    .factory('recoverPass', [
        function($resource){
            return $resource('/signup/fetchData/recoverPass', {}, {
                recoverPassword: { method: 'POST'}
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
    ])
//DELETE THIS
    .directive('btnSuccess', ['signUpAction',
        function (signUpAction) {//
            return {
                restrict: 'C',
                link: function (scope, elem) {
                    elem.bind('click', function (e) {
                        signUpAction.signUp(scope.profile.username, scope.profile.password);
                    });
                }
            };
        }
    ])
    .directive('doReg', ['signUpAction',
        function (signUpAction) {
            return {
                restrict: 'C',
                link: function (scope, elem) {
                    elem.bind('keypress', function (event) {
                        if (event.which === 13) {
                            signUpAction.signUp(scope.profile.username, scope.profile.password);
                        }
                    });
                }
            };
        }
    ]);
