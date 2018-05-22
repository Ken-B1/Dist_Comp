(function () {

    var app = angular.module("foodApp");

    var BoardsController = function ($scope, $http, $routeParams) {
        var username = $routeParams.username;
        //console.log("username is "+username)
        
        var onUserComplete = function(response){
            $scope.user = $routeParams.username
            $scope.boards = response.data;
        };
        var onError = function(reason){
            $scope.error = reason
        };
        var req = {
            method: 'GET',
            url: 'http://localhost:8080/restApi/api/board/'+username,
            headers: {
                'Content-Type': 'application/json'
            },
            data:undefined
            };

        $http(req).then(onUserComplete, onError);

        
    }

app.controller("BoardsController",BoardsController);
}());