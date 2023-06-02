package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.entities.Recipes;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    //First checking whether the recipe exists based on the recipe name from the recipes request body and if exists throws an custom exception. If not saves the records.
    Recipes addRecipes(Recipes recipes);

    //Lists all the recipes.
    public List<Recipes> getAllRecipes();


    Recipes getRecipeByName(String recipename);

    Set<Recipes> getRecipeByEmail(String emailid);

    Set<Recipes> getRecipeByIngName(String ingname);

    void deleteRecipe(String recipename);

    int getRecipesCount();
}
