package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.RecipeNotFound;
import com.superchef.Super.Chef.Exceptions.recipeAlreadyexists;
import com.superchef.Super.Chef.daos.FavRecipesDao;
import com.superchef.Super.Chef.daos.RecipeDao;
import com.superchef.Super.Chef.daos.UserDao;
import com.superchef.Super.Chef.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private FavRecipesDao favRecipeDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Recipes addRecipes(Recipes recipes) {

        Recipes recipe = recipeDao.findByrecipeName(recipes.getRecipeName());
        if (recipe != null) {
            throw new recipeAlreadyexists("Recipe is Already present in the table.");
        }
        return recipeDao.save(recipes);
    }

    public List<Recipes> getAllRecipes() {
        return recipeDao.findAll();
    }

    public Recipes getRecipeByName(String recipename) {
        Recipes recipe = recipeDao.findByrecipeName(recipename);
        if (recipe == null) {
            throw new RecipeNotFound("Recipe Not Found for the provided recipe Name");
        }
        return recipe;
    }

    @Override
    public Set<Recipes> getRecipeByIngName(String ingname) {
        Set<Recipes> newRecipelst = new HashSet<>();
        for (Recipes recipe : recipeDao.findAll()) {
            if (recipe.getRecipeIng().toLowerCase().contains(ingname.toLowerCase())) {
                newRecipelst.add(recipe);
            }
        }

        if (newRecipelst.size() == 0) {
            throw new RecipeNotFound("No recipe Found for the provided ingredient");
        }

        return newRecipelst;



    }

    @Override
    public Set<Recipes> getRecipeByEmail(String emailid) {
        Optional<User> result = userDao.findById(emailid);
        Set<Recipes> newRecipeSet =  new HashSet<>();
        if(result.isPresent()){
            User user = result.get();
            for(UserIng mapping:user.getUseringMapping()){
                for(Recipes recipe: recipeDao.findAll()){
                    if(recipe.getRecipeIng().toLowerCase().contains(mapping.getIng().getIngName().toLowerCase())){
                        newRecipeSet.add(recipe);
                    }
                }
            }

            if(newRecipeSet.size() == 0){
                throw new RecipeNotFound("No recipe Found for the provided ingredient");
            }

        }

        return newRecipeSet;
    }

    public void deleteRecipe(String recipename) {
        Recipes recipe = recipeDao.findByrecipeName(recipename);
        Set<FavRecipes> favRecipeSet = null;
        if (recipe == null) {
            throw new RecipeNotFound("Recipe Not Found For the Recipe Name.");
        } else {
            favRecipeSet = favRecipeDao.findByfavrecipeName(recipename);
            if (favRecipeSet != null) {
                for (FavRecipes favrecipe : favRecipeSet) {
                    Set<User_Fav_Recipes> users = favrecipe.getFavMappings();
                    for (User_Fav_Recipes user : users) {
                        user.getUser().getUserMapping().remove(user);
                    }
                }
                favRecipeDao.deleteAll(favRecipeSet);
            } else {
                throw new RecipeNotFound("Recipe Not Found");
            }

        }

        recipeDao.deleteById(recipe.getRecipeId());
    }

    public int getRecipesCount() {
        int count = recipeDao.findAll().size();
        if (count == 0) {
            throw new RecipeNotFound("No Recipes Found");
        }
        return count;
    }
}
