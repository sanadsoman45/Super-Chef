package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.entities.FavRecipes;

public interface FavRecipeService {

    FavRecipes addFavRecipe(String emailid, String recipeName);

}
