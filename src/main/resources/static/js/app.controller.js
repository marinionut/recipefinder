/**
 * Created by ionut on 1/2/2018.
 */
/**
 * Created by ionut on 6/2/2017.
 */
(function (module) {

    var RecipeFinderCtrl = function (recipeFinderService, $scope) {
        $scope.ingredients = [];
        $scope.recipes = [];

        $scope.getRecipes = function () {

            console.log("ingredients:" + $scope.ingredients);

            //extract search tags
            $scope.ingredients = document.getElementById('ingredients').value;
            console.log("ingredients:" + $scope.ingredients);


            recipeFinderService.getRecipes($scope.ingredients).success(function (data) {
                $scope.recipes = data;
                console.log($scope.recipes);
            });

            // recipeFinderService.getCachedRecipes().success(function (data) {
            //     $scope.recipes = data;
            //     console.log($scope.recipes);
            // });

        };
    };

    module.controller("recipeFinderCtrl", RecipeFinderCtrl);

}(angular.module("recipeFinderApp")));
