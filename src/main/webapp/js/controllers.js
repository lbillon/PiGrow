function HomeCtrl($scope, $routeParams) {

}

function AlertCtrl($scope) {

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };

}

function MainCtrl($scope, $routeParams, $location, $http) {

    $scope.logUserIn = function(data) {

        $scope.user = data;
        console.log(data);

    }

    $scope.addAlert = function(message, type) {
        $scope.alerts.push({type: type, msg: message});
    };

    $scope.resetAlerts = function() {
        $scope.alerts = [];
    };

    $scope.alerts = [];
}


function PanelCtrl($http, $scope, $timeout, apis) {

    $scope.autoLighting = false;
    $scope.autoLightingThreshold = 0;
    $scope.lightingValue = 0;
    $scope.lightingMode = '';



    $scope.nextLighting = function() {
        $http({method: 'GET', url: contextPath + '/api/nextlightingstate.do'}).success(function() {
            $scope.getStatus();
        });
    }

    $scope.water = function() {
        $http({method: 'GET', url: contextPath + '/api/water.do'});
    }


    $scope.setAutoLightingThreshold = function() {
        $http({method: 'POST', url: contextPath + '/api/autolighting.do', data: $.param({t: $scope.autoLightingThreshold}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function() {
            $scope.getStatus();
        });
    }

    $scope.getStatus = function() {
        $http({method: 'GET', url: contextPath + '/api/status.do'}).success(function(data) {
            $scope.autoLightingThreshold = data.autoLightingThreshold;
            $scope.lightingValue = data.ambientLighting;
            $scope.lighting = data.lighting;
            $scope.lightingMode = data.lightingState;
        });
    }

    $scope.getStatus();
}


function LoginCtrl($scope, $routeParams, $location, $http) {



    $scope.login = function() {


        $scope.d.action = 'login',
                $http({
            method: 'POST',
            url: "/do", data: $.param($scope.d),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(function(data) {
            $location.path(contextPath + '/catalogue');

            $scope.logUserIn(data);
        }).
                error(function(data) {
            $scope.addAlert('Identifiants incorrects!', 'error');
        });

    }
}


