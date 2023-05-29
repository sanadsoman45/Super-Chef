package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.entities.FavRecipes;

import java.util.Set;

public interface FavRecipeService {

    //Return FAvRecipes Set after adding to database.
    FavRecipes addFavRecipe(String emailid, String recipeName);

    //Returning Set of FavRecipes based on user email-id and recipename
    Set<FavRecipes> getFavRecipes(String emailid, String recipename);

    //Retrieve the set of FavRecipes based on emailid provided.
    public Set<FavRecipes> getFavRecipesByEmailid(String emailid);

    //Retrieve the set of FavRecipes based on emailid and ing name provided.
    public Set<FavRecipes> getFavRecipesByIng(String emailid,String ingName);

    //Retrieve the count of favrecipes based on emailid for each user.
    public int getFavRecipesCount(String emailid);

}
