package ro.ionutmarin.service;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ro.ionutmarin.model.Recipe;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by ionut on 12/31/2017.
 */
@Service
public class RecipeService {
    private static final String BASE_URL = "https://api.edamam.com/search";
    private static final String APP_KEY = "7a135792f5ddcc171332c4d7ba9f498f";
    private static final String APP_ID = "4be160f0";

    public ArrayList<Recipe> findRecipes(String ingredients) {
        String url = urlBuilder(ingredients);
        ArrayList<Recipe> recipes = null;
        try {
            JSONObject json = new JSONObject(IOUtils.toString(new URL(urlBuilder(ingredients)), Charset.forName("UTF-8")));
            recipes = processResults(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    public String urlBuilder(String ingredients) {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL + "?" + "q=" + ingredients.trim()
                        + "&app_id=" + APP_ID + "&app_key=" + APP_KEY);
        return url.toString();
    }

    public ArrayList<Recipe> processResults(JSONObject returnJSON) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        JSONArray recipesJSON = returnJSON.getJSONArray("hits");
        for (int i = 0; i < 10; i++) {
            JSONObject recipeArrayJSON = recipesJSON.getJSONObject(i);
            JSONObject recipeJSON = recipeArrayJSON.getJSONObject("recipe");
            String name = recipeJSON.getString("label");
            String imageUrl = recipeJSON.getString("image");
            String sourceUrl = recipeJSON.getString("url");
            String ingredients = recipeJSON.getJSONArray("ingredientLines").toString();
            double calories = recipeJSON.getDouble("calories");

            Recipe recipe = new Recipe (name, imageUrl, sourceUrl, ingredients, calories);
            recipes.add(recipe);
        }

        return recipes;
    }

    public static void main (String args[]) {
        RecipeService recipeService = new RecipeService();
        ArrayList<Recipe> recipes = recipeService.findRecipes("potato");

        System.out.println(recipes.toString());
    }
}
