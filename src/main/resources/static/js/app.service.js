/**
 * Created by ionut on 1/2/2018.
 */
/**
 * Created by ionut on 6/2/2017.
 */
(function (module) {

    var recipeFinderService = function ($http) {

        var headers = {
            'Access-Control-Allow-Origin' : '*',
            'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
            'Accept': 'application/json'
        };

        var param_check = function(ingredients) {
            if (ingredients == undefined || ingredients == null)
                window.alert("Ingredients list is empty!");
        };

        var getRecipes = function (ingredients) {
            param_check(ingredients);

            var url = "http://localhost:8080/recipe?" + "ingredients=" + ingredients;

            return $http({
                url: url,
                method: 'GET',
                headers: headers
            });
        };

        var getCachedRecipes = function () {
            var url = "http://localhost:8080/recipe/cached";

            return $http({
                url: url,
                method: 'GET',
                headers: headers
            });
        };


        return {
            getRecipes: getRecipes,
            getCachedRecipes: getCachedRecipes
        };
    };

    module.factory("recipeFinderService", recipeFinderService);
}(angular.module("recipeFinderApp")));
