function MainCtrl($scope, $routeParams, $location, $http) {

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


    var poll = function() {

        $timeout(function() {
            $scope.getStatus();
            poll();
        }, 10000);
    };

    poll();
    $scope.getStatus();
}

