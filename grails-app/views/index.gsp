<!DOCTYPE html>
<html lang="en" ng-app="app" style="background-color: #d8d8d8; font-size: 15px;">
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properities -->
    <!-- Change title according to section -->
    <title>Ziffer</title>

    <!-- Style sheets -->
    <link rel="stylesheet" type="text/css" href="dist/semantic.min.css">

    <!-- Mathjax config -->
    <script type="text/x-mathjax-config">
      MathJax.Hub.Config({
        showProcessingMessages: false,
        tex2jax: { inlineMath: [['$','$'],['\\(','\\)']] },
        processEscapes: true
      });
    </script>

    <script src="dist/jQuery/jquery-2.1.3.min.js"></script>
    <script src="dist/semantic.min.js"></script>
    <script src="dist/angular/angular.js"></script>
    <script src="dist/MathJax/MathJax.js?config=TeX-AMS_HTML"></script>

    <!-- Resource modules -->
    <r:require module="resources"/>
    <r:layoutResources/>

</head>
<body style="background-color: #d8d8d8">
<!-- Header -->
<div class="topBar">
    <div class="ui inverted borderless topBarNotLogged menu fixed" ng-show="!$root.$storage.loggedUser">
        <a class="item" href="#/">Ziffer</a>
        <div class="right menu">
            <a class="item" href="#/signup">Sign up</a>
            <a class="item" href="#/signin">Sign in</a>
        </div>
    </div>
    <div class="ui inverted borderless topBarLogged menu fixed" ng-show="$root.$storage.loggedUser">
        <a class="item" href="#/">Ziffer</a>
        <div class="right menu">
            <a class="item ui dropdown" id="loggedMenu" ng-cloak>
                {{$root.$storage.username}}
                <div class="menu" ng-controller="IndexCtrl">
                    <div class="item" ng-click="goDash()">Dashboard</div>
                    <div class="item" ng-click="goEdit()">Edit Profile</div>
                    <div class="item" ng-click="goMyQuestions()">My questions</div>
                    <div class="item" ng-click="goMyContributions()">My contributions</div>
                    <div class="item" ng-click="goSignout()">Sign out</div>
                </div>
            </a>
        </div>
    </div>
</div>
<!-- Main body -->
<div ng-view id="main-body" class="slide"></div>
<r:layoutResources/>
<!-- Footer -->
<div class="ui basic segment"></div>
</body>

</html>