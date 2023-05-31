package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.entities.FavRecipes;
import com.superchef.Super.Chef.entities.User;
import com.superchef.Super.Chef.entities.User_Fav_Recipes;

import java.util.Optional;
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

    //delete RecipeByName and emailid
    public void deleteRecipeByName(String emailid, String recipeName);

    //delete all recipes of emailid
    public void deleteAllRecipe(String emailid);

}
