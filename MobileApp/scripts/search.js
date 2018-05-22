(function () {

    var app = angular.module("foodApp");

    var SearchController = function ($scope, $http, $routeParams) {
        var username = $routeParams.username;
        //console.log("username is "+username)

        var onUserComplete = function (response) {
            $scope.user = $routeParams.username
            $scope.results = response.data;
        };
        var onError = function (reason) {
            $scope.error = reason
        };

        $scope.search = function () {
            var req = {
                method: 'GET',
                url: 'http://localhost:8080/restApi/api/search/'+$scope.searchString,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: undefined
            };

            $http(req).then(onUserComplete, onError);

        }
    }

    app.controller("SearchController", SearchController);
}());