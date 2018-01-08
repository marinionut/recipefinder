package ro.ionutmarin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.ionutmarin.model.Recipe;
import ro.ionutmarin.service.DBService;
import ro.ionutmarin.service.RecipeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static ro.ionutmarin.service.DBService.insertRecipe;

/**
 * Created by ionut on 12/31/2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    public RecipeService recipeService;

    private static ArrayList<Recipe> recipes;

    @RequestMapping(method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ArrayList<Recipe> findRecipes(@RequestParam(value="ingredients", required=true) String ingredients) {
        ArrayList<Recipe> recipesList = null;
        try {
            recipesList = recipeService.findRecipes(ingredients);
            recipes = recipesList;

            insertRecipe(DBService.connectToDB("db"), ingredients, recipes);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            recipesList = DBService.getRecipeByIngredients(DBService.connectToDB("db"), ingredients);
        } finally {
            return recipesList;
        }
    }


    @RequestMapping(value = "/cached", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ArrayList<Recipe> getRecipes() {
        return recipes;
    }

}
