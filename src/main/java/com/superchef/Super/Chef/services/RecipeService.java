package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.entities.Recipes;

import java.util.List;

public interface RecipeService {

    //First checking whether the recipe exists based on the recipe name from the recipes request body and if exists throws an custom exception. If not saves the records.
    Recipes addRecipes(Recipes recipes);

    //Lists all the recipes.
    public List<Recipes> getAllRecipes();


    Recipes getRecipeByName(String recipename);

    List<Recipes> getRecipeByIngName(String ingname);

    void deleteUser(String recipename);

    int getRecipesCount();
}
