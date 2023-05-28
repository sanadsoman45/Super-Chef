package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.RecipeNotFound;
import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.daos.FavRecipesDao;
import com.superchef.Super.Chef.daos.RecipeDao;
import com.superchef.Super.Chef.daos.UserDao;
import com.superchef.Super.Chef.entities.FavRecipes;
import com.superchef.Super.Chef.entities.Recipes;
import com.superchef.Super.Chef.entities.User;
import com.superchef.Super.Chef.entities.User_Fav_Recipes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FavRecipeServiceImpl implements FavRecipeService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private FavRecipesDao favRecipesDao;

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private User user;

    @Autowired
    private FavRecipes favRecipes;

    @Autowired
    private User_Fav_Recipes userfavrecipes;

    @Override
    public FavRecipes addFavRecipe(String emailid, String recipeName) {
        Optional<User> result = userDao.findById(emailid);
        Recipes recipe = recipeDao.findByrecipeName(recipeName);
        if(result.isPresent()){
            if(recipe!=null){
                user = result.get();
                favRecipes.setFavrecipeName(recipe.getRecipeName());
                favRecipes.setFavimageUrl(recipe.getImageUrl());
                favRecipes.setFavrecipeIng(recipe.getRecipeIng());
                favRecipes.setFavrecipeInstr(recipe.getRecipeInstr());
                favRecipes.setFavcookTime(recipe.getCook_time());
                favRecipes.setFavprepTime(recipe.getPrepTime());
                favRecipes.setFavtotalTime(recipe.getTotal_time());
                userfavrecipes.setUser(user);
                userfavrecipes.setFavRecipes(favRecipes);
                user.getUserMapping().add(userfavrecipes);
                favRecipes.getFavMappings().add(userfavrecipes);


            }
            else{
                throw new RecipeNotFound("Not Found For "+recipeName);
            }
        }
        else{
            throw new userNotFound("User Not Found for "+emailid);
        }
        return null;
    }
}
