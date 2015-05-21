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

    <!-- Referencias globales -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

    <!-- Agregar a modules -->
    <r:require module="angular"/>
    <r:layoutResources/>

</head>
<body style="background-color: #d8d8d8">
<!-- Header -->
<div class="topBar">
    <div class="ui inverted borderless topBarNotLogged menu fixed">
        <a class="item" href="#/">Ziffer</a>
        <div class="right menu">
            <a class="item" href="#/signup">Sign up</a>
            <a class="item" href="#/signin">Sign in</a>
        </div>
    </div>
    <div class="ui inverted borderless topBarLogged menu fixed">
        <a class="item" href="#/">Ziffer</a>
        <div class="right menu">
            <a class="item ui dropdown" id="loggedMenu">
                {{username}}
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