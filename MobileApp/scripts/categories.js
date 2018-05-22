(function () {

    var app = angular.module("foodApp");

    var CatController = function ($scope, $http, $routeParams) {
        var username = $routeParams.username;
        //console.log("username is "+username)
        
        var onUserComplete = function(response){
            $scope.user = $routeParams.username
            $scope.cats = response.data;
        };
        var onError = function(reason){
            $scope.error = reason
        };
        var req = {
            method: 'GET',
            url: 'http://localhost:8080/restApi/api/categories',
            headers: {
                'Content-Type': 'application/json'
            },
            data:undefined
            };

        $http(req).then(onUserComplete, onError);

        
    }

app.controller("CatController",CatController);
}());