(function () {

    var app = angular.module("foodApp");

    var MainController = function ($scope, $http, $location) {

        var onUserComplete = function (response) {
            $scope.user = response.data;
            //console.log($location.url());
            $location.path('profile/'+$scope.username);            
        };
        var onError = function (reason) {
            $scope.error = reason
        };

        // console.log(req);
        $scope.login = function () {
            var username = $scope.username;
            var password = $scope.password;
            
            var req = {
                method: 'POST',
                url: 'http://localhost:8080/restApi/api/login/',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({
                    "username": username, "password": password
                })
            };
            
            $http(req).then(onUserComplete, onError);

        };
    }

    app.controller("MainController", MainController);
}());