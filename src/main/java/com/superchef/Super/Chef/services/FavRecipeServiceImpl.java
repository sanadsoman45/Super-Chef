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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FavRecipeServiceImpl implements FavRecipeService{


    //Autowiring the references of entity classes and dao interfaces.
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
        //Finding the user based on the meail-id.
        Optional<User> result = userDao.findById(emailid);
        //getting the list of recipes based on recipe name.
        Recipes recipe = recipeDao.findByrecipeName(recipeName);
        //if condition checks whether the result is null or not.
        if(result.isPresent()){
            if(recipe!=null){
                //Retrieveing the user based on the email-id provided.
                user = result.get();

                //Setting the data retrieved from recipes into favRecipes entity for saving it into database.
                favRecipes.setFavrecipeName(recipe.getRecipeName());
                favRecipes.setFavimageUrl(recipe.getImageUrl());
                favRecipes.setFavrecipeIng(recipe.getRecipeIng());
                favRecipes.setFavrecipeInstr(recipe.getRecipeInstr());
                favRecipes.setFavcookTime(recipe.getCook_time());
                favRecipes.setFavprepTime(recipe.getPrepTime());
                favRecipes.setFavtotalTime(recipe.getTotal_time());
                //Saving the favRecipes object in database and returning the same.
                favRecipes= favRecipesDao.save(favRecipes);

                //Set of type favrecipes which will be passed to user entity for setting link with favRecipes class.
                Set<User_Fav_Recipes> favRecipesSet =new HashSet<>();
                //Setting the retrieved user and saved favrecipes to the mapping entity.
                userfavrecipes.setUser(user);
                userfavrecipes.setFavRecipes(favRecipes);
                //Adding the favRecipes data to the set of type favRecipes.
                favRecipesSet.add(userfavrecipes);
                //Setting the mappings from user to FavRecipes by passing the FavRecipeSet created earlier.
                user.setUserMapping(favRecipesSet);
                //Saving the user to the database.
                userDao.save(user);
                System.out.println(user);


            }
            else{
                throw new RecipeNotFound("Not Found For "+recipeName);
            }
        }
        else{
            throw new userNotFound("User Not Found for "+emailid);
        }
        return favRecipesDao.save(favRecipes);
    }
}
