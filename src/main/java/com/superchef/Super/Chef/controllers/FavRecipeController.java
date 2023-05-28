package com.superchef.Super.Chef.controllers;

import com.superchef.Super.Chef.entities.FavRecipes;
import com.superchef.Super.Chef.services.FavRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/superchef")
@RestController
public class FavRecipeController {

    @Autowired
    FavRecipeService favRecipeService;

    @PostMapping("/user/{emailid}/favrecipes/{recipeName}")
    public FavRecipes addRecipes(@PathVariable  String emailid,@PathVariable String recipeName){
        return favRecipeService.addFavRecipe(emailid,recipeName);
    }
}
