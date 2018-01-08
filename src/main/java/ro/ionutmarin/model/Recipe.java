package ro.ionutmarin.model;

import java.util.Arrays;

/**
 * Created by ionut on 12/31/2017.
 */
public class Recipe {
    private String name;
    private String imageUrl;
    private String sourceUrl;
    private String ingredients;
    private double calories;

    public Recipe() {
    }

    public Recipe(String name, String imageUrl, String sourceUrl, String ingredients, double calories) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.sourceUrl = sourceUrl;
        this.ingredients = ingredients.replaceAll("\\[","").replaceAll("\\]","")
                .replaceAll("\\\\","").replaceAll("\",\"",", ");
        //this.ingredients = ingredients;
        this.calories = calories;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", ingredients=" + ingredients +
                ", calories=" + calories +
                '}';
    }
}
