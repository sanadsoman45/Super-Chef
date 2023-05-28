package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.RecipeNotFound;
import com.superchef.Super.Chef.Exceptions.recipeAlreadyexists;
import com.superchef.Super.Chef.daos.RecipeDao;
import com.superchef.Super.Chef.entities.Recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Override
    public Recipes addRecipes(Recipes recipes) {

        Recipes recipe = recipeDao.findByrecipeName(recipes.getRecipeName());
        if(recipe != null){
            throw new recipeAlreadyexists("Recipe is Already present in the table.");
        }
        return recipeDao.save(recipes);
    }

    public List<Recipes> getAllRecipes(){
        return recipeDao.findAll();
    }

    public Recipes getRecipeByName(String recipename){
        Recipes recipe = recipeDao.findByrecipeName(recipename);
        if(recipe == null){
            throw new RecipeNotFound("Recipe Not Found for the provided recipe Name");
        }
        return recipe;
    }

    @Override
    public List<Recipes> getRecipeByIngName(String ingname) {
        List<Recipes> newRecipelst =  new ArrayList<>();
        for(Recipes recipe : recipeDao.findAll()){
            if(recipe.getRecipeIng().toLowerCase().contains(ingname.toLowerCase())){
                newRecipelst.add(recipe);
            }
        }

        if(newRecipelst.size() == 0){
            throw new RecipeNotFound("No recipe Found for the provided ingredient");
        }
        
        return newRecipelst;
    }

    public void deleteUser(String recipename){
        Recipes recipe = recipeDao.findByrecipeName(recipename);
        if(recipe == null){
            throw new RecipeNotFound("Recipe Not Found For the Recipe Name.");
        }
        recipeDao.deleteById(recipe.getRecipeId());
    }

    public int getRecipesCount(){
        int count = recipeDao.findAll().size();
        if(count == 0){
            throw new RecipeNotFound("No Recipes Found");
        }
        return count;
    }
}
