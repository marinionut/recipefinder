/**
 * Created by ionut on 1/2/2018.
 */
/**
 * Created by ionut on 6/5/2017.
 */
(function (module) {
    // 'use strict';

    recipeView = function recipeView() {
        var directive = {
            restrict: 'E',
            controller: 'recipeFinderCtrl',
            scope: {
                recipe: '=' //TO CHANGE
            },
            replace: true,
            templateUrl: "views/recipeView.html" // TO CHANGE
        };

        return directive;
    }

    module.directive('recipeView', recipeView);

}(angular.module("recipeFinderApp")));


