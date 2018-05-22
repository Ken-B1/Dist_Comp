(function () {

    var app = angular.module("foodApp");

    var PinsController = function ($scope, $http, $routeParams) {
        var boardid = $routeParams.boardid;
        //console.log("username is "+username)
        
        var onUserComplete = function(response){
            $scope.user = $routeParams.username;
            $scope.pins = response.data;
        };
        var onError = function(reason){
            $scope.error = reason
        };
        var req = {
            method: 'GET',
            url: 'http://localhost:8080/restApi/api/pin/board/'+boardid,
            headers: {
                'Content-Type': 'application/json'
            },
            data:undefined
            };

        $http(req).then(onUserComplete, onError);

        
    }

app.controller("PinsController",PinsController);
}());