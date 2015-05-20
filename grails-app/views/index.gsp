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
<div class="ui inverted borderless top-bar menu fixed">
    <a class="item" href="#/">Ziffer</a>
    <div class="right menu">
        <a class="item" href="#/signup">Sign up</a>
        <a class="item" href="#/signin">Sign in</a>
    </div>
</div>
<!-- Main body -->
<div ng-view id="main-body"></div>
<r:layoutResources/>
<!-- Footer -->
<div class="ui basic segment"></div>
</body>

</html>