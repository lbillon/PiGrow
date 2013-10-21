<!DOCTYPE html>
<html ng-app="appj">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title></title>
        <link rel='stylesheet' media='screen' href='${pageContext.request.contextPath}/webjars/bootstrap/3.0.0/css/bootstrap.min.css'>
        <script type='text/javascript' src='${pageContext.request.contextPath}/webjars/jquery/1.9.0/jquery.min.js'></script>
        <script type='text/javascript' src='${pageContext.request.contextPath}/webjars/bootstrap/3.0.0/js/bootstrap.min.js'></script>
        <script src="${pageContext.request.contextPath}/webjars/angularjs/1.1.5/angular.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.jsp"></script>
        <script src="${pageContext.request.contextPath}/js/controllers.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/3.2.1/css/font-awesome.min.css">

        <style>
            .fw-container {
                padding-left: 15px;
                padding-right: 15px;
                margin-left: auto;
                margin-right: auto;
            }
        </style>


    </head>

    <body>

        <!--    <div class="navbar navbar-inverse navbar-fixed-top">
              <div class="container">
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" href="#">Project name</a>
                </div>
                <div class="navbar-collapse collapse">
                  <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                      <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                      </ul>
                    </li>
                  </ul>
                  <form class="navbar-form navbar-right">
                    <div class="form-group">
                      <input type="text" placeholder="Email" class="form-control">
                    </div>
                    <div class="form-group">
                      <input type="password" placeholder="Password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                  </form>
                </div>/.navbar-collapse 
              </div>
            </div>-->

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="fw-container" ng-controller="MainCtrl">
            <div ng-view style="min-height:100%"></div>
        </div>
    </div>

</body>
</html>
