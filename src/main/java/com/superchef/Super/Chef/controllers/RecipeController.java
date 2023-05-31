package com.superchef.Super.Chef.controllers;

import com.superchef.Super.Chef.entities.Recipes;
import com.superchef.Super.Chef.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superchef")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/recipes")
    public Recipes addRecipes(@RequestBody Recipes recipes) {
        return recipeService.addRecipes(recipes);
    }

    @GetMapping("/recipes")
    public List<Recipes> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/getrecipes/{recipename}")
    public Recipes getRecipeByName(@PathVariable String recipename){
        return recipeService.getRecipeByName(recipename);
    }

    @GetMapping("/recipes/{ingname}")
    public List<Recipes> getRecipeByIngName(@PathVariable String ingname){
        return recipeService.getRecipeByIngName(ingname);
    }


    @DeleteMapping("/recipes/{recipename}")
    public String deleteUser(@PathVariable String recipename){
        recipeService.deleteRecipe(recipename);
        return "Recipe deleted for "+recipename;
    }

    @GetMapping("/countrecipes")
    public String getRecipesCount(){
        return "Total Recipe Count is: "+recipeService.getRecipesCount();
    }

}
