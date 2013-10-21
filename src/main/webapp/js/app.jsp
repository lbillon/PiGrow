var app = angular.module('appj', []);

var contextPath = "${pageContext.request.contextPath}";

app.service('apis', function($http) {
this.get = function() {
return    $http({method: 'GET',
url: "${pageContext.request.contextPath}/api/hello.do"}).error(function() {
console.log('error')
})
then(function(result) {
return(result.data);
});
};
});


app.config(['$routeProvider', function($routeProvider) {
$routeProvider.
when('/', {templateUrl: 'partials/panel.html', controller: PanelCtrl}).
otherwise({redirectTo: '/'});
}]);

app.directive('ghBargraph', function() {
return function(scope, element, attrs) {
scope.$watch(attrs.ghBargraph, function(value) {
});

}
});



