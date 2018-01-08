package ro.ionutmarin.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.ionutmarin.model.Recipe;
import ro.ionutmarin.service.RecipeService;

import java.util.ArrayList;

/**
 * Created by ionut on 12/31/2017.
 */
@RestController
@RequestMapping("/status")
public class StatusController {

    @RequestMapping(method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findRecipes() {
        return "Application is up & running!";
    }
}
