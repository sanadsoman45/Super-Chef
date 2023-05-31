package com.superchef.Super.Chef.controllers;

import com.superchef.Super.Chef.entities.FavRecipes;
import com.superchef.Super.Chef.services.FavRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/superchef")
public class FavRecipeController {

    @Autowired
    FavRecipeService favRecipeService;

    @PostMapping("/user/{emailid}/favrecipes/{recipeName}")
    public FavRecipes addRecipes(@PathVariable  String emailid,@PathVariable String recipeName){
        return favRecipeService.addFavRecipe(emailid,recipeName);
    }

    @GetMapping("/user/{emailid}/favrecipes/{recipename}")
    public Set<FavRecipes> getFavRecipes(@PathVariable String emailid, @PathVariable String recipename){
        return favRecipeService.getFavRecipes(emailid,recipename);
    }

    @GetMapping("/user/{emailid}")
    public Set<FavRecipes> getFavRecipesByEmailid(@PathVariable String emailid){
        return favRecipeService.getFavRecipesByEmailid(emailid);
    }

    @GetMapping("/user/{emailid}/recipeing/{ingname}")
    public Set<FavRecipes> getFavRecipesByIng(@PathVariable String emailid,@PathVariable String ingname){
        return favRecipeService.getFavRecipesByIng(emailid,ingname);
    }

    @GetMapping("/favrecipe/{emailid}")
    public int getFavRecipesCount(@PathVariable String emailid){
        return favRecipeService.getFavRecipesCount(emailid);
    }

    @DeleteMapping("/users/{emailid}/recipe/{recipename}")
    public void deleteRecipeByName(@PathVariable String emailid, @PathVariable String recipename){
        favRecipeService.deleteRecipeByName(emailid,recipename);
    }

    @DeleteMapping("/users/favrecipe/{emailid}")
    public void deleteRecipe(@PathVariable String emailid){
        favRecipeService.deleteAllRecipe(emailid);
    }

}
