(function () {

    var app = angular.module("foodApp", ["ngRoute"]);

    app.config(function ($routeProvider) {

        $routeProvider
            .when("/main", {
                templateUrl: "/index.html",
                controller: "MainController"
            })
            .when("/profile/:username", {
                templateUrl: "/profile.html",
                controller: "UserController"
            })
            .when("/boards/:username",{
                templateUrl: "/boards.html",
                controller: "BoardsController"
            })
            .when("/pins/:username/:boardid",{
                templateUrl: "/pins.html",
                controller: "PinsController"
            })
            .when("/categories/:username",{
                templateUrl: "/categories.html",
                controller: "CatController"
            })
            .when("/search/:username",{
                templateUrl: "/search.html",
                controller: "SearchController"
            })
            .otherwise({ redirectTo: "/main" });
    });

}());