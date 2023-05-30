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
    private User_Fav_Recipes userfavrecipes;

    @Override
    public FavRecipes addFavRecipe(String emailid, String recipeName) {
        //Finding the user based on the meail-id.
        Optional<User> result = userDao.findById(emailid);
        //getting the list of recipes based on recipe name.
        Recipes recipe = recipeDao.findByrecipeName(recipeName);
        FavRecipes favRecipes = null;
        //if condition checks whether the result is null or not.
        if(result.isPresent()){
            if(recipe!=null){
                //Retrieveing the user based on the email-id provided.
                user = result.get();
                favRecipes = new FavRecipes();
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
            }
            else{
                throw new RecipeNotFound("Not Found For "+recipeName);
            }
        }
        else{
            throw new userNotFound("User Not Found for "+emailid);
        }
        return favRecipes;
    }

    @Override
    public Set<FavRecipes> getFavRecipes(String emailid, String recipename) {
        Optional<User> result = userDao.findById(emailid);
        Set<FavRecipes> favRecipeSet = new HashSet<>();
        if(result.isPresent()){
            user = result.get();
            for(User_Fav_Recipes mapping:user.getUserMapping()){
                if(mapping.getUser().getUserEmail().equalsIgnoreCase(emailid) && mapping.getFavRecipes().getFavrecipeName().equalsIgnoreCase(recipename)){
                    favRecipeSet.add(mapping.getFavRecipes());
                }
            }
            System.out.println(favRecipeSet);
            if(favRecipeSet.size()==0){
                throw new RecipeNotFound("No Recipes Found.");
            }
        }
        else{
            throw new userNotFound("User not Found for "+emailid);
        }
        return favRecipeSet;
    }

    @Override
    public Set<FavRecipes> getFavRecipesByEmailid(String emailid) {
        Optional<User> result = userDao.findById(emailid);
        Set<FavRecipes> favRecipeSet = new HashSet<>();
        if(result.isPresent()){
            user = result.get();
            System.out.println("Inside the method.");
            for(User_Fav_Recipes mapping:user.getUserMapping()){
                if(mapping.getUser().getUserEmail().equalsIgnoreCase(emailid)){
                    favRecipeSet.add(mapping.getFavRecipes());
                }
            }
            System.out.println(favRecipeSet);
            if(favRecipeSet.size()==0){
                throw new RecipeNotFound("No Recipes Found.");
            }
        }
        else{
            throw new userNotFound("User not Found for "+emailid);
        }
        return favRecipeSet;
    }

    @Override
    public Set<FavRecipes> getFavRecipesByIng(String emailid, String ingName) {
        Optional<User> result = userDao.findById(emailid);
        Set<FavRecipes> favRecipeSet = new HashSet<>();
        if(result.isPresent()){
            user = result.get();
            System.out.println("Inside the method.");
            for(User_Fav_Recipes mapping:user.getUserMapping()){
                if(mapping.getUser().getUserEmail().equalsIgnoreCase(emailid) && mapping.getFavRecipes().getFavrecipeIng().toLowerCase().contains(ingName.toLowerCase())){
                    favRecipeSet.add(mapping.getFavRecipes());
                }
            }
            System.out.println(favRecipeSet);
            if(favRecipeSet.size()==0){
                throw new RecipeNotFound("No Recipes Found.");
            }
        }
        else{
            throw new userNotFound("User not Found for "+emailid);
        }
        return favRecipeSet;
    }

    public int getFavRecipesCount(String emailid){
        Optional<User> result = userDao.findById(emailid);
        Set<FavRecipes> favRecipeSet = new HashSet<>();
        if(result.isPresent()){
            user = result.get();
            System.out.println("Inside the method.");
            for(User_Fav_Recipes mapping:user.getUserMapping()){
                if(mapping.getUser().getUserEmail().equalsIgnoreCase(emailid)){
                    favRecipeSet.add(mapping.getFavRecipes());
                }
            }
            if(favRecipeSet.size()==0){
                throw new RecipeNotFound("No Recipes Found.");
            }
        }
        else{
            throw new userNotFound("User not Found for "+emailid);
        }
        return favRecipeSet.size();
    }

    public void deleteRecipeByName(String emailid, String recipeName){
        Optional<User> result = userDao.findById(emailid);
        int favRecipeId = 0;
        if(result.isPresent()){
            user = result.get();
            for(User_Fav_Recipes mapping:user.getUserMapping()){
                System.out.println("Inside the for loop");
                if(mapping.getUser().getUserEmail().equalsIgnoreCase(emailid) && mapping.getFavRecipes().getFavrecipeName().equalsIgnoreCase(recipeName)){
                    user.getUserMapping().remove(mapping);
                    favRecipeId = mapping.getFavRecipes().getFavrecipeId();
                    System.out.println("Internally:"+favRecipeId);

                    System.out.println("Inside if ");

                    break;
                }
            }
            favRecipesDao.deleteById(favRecipeId);
            System.out.println("Fav Recipe ID is:"+favRecipeId);


        }
        else{
            throw new userNotFound("User not Found for "+emailid);
        }

    }

    public void deleteAllRecipe(String emailid){
        Optional<User> result = userDao.findById(emailid);
        if(result.isPresent()){
            user = result.get();
            int id =0;

            for(User_Fav_Recipes mapping: user.getUserMapping()){
                if(mapping.getUser().getUserEmail().toLowerCase().contains(emailid.toLowerCase())){
                    System.out.println("Inside if-for loop");
                    id = mapping.getFavRecipes().getFavrecipeId();
                    user.getUserMapping().remove(mapping);
                    System.out.println("emailid is:"+emailid+"userid is:"+id);
                }
                System.out.println("Inside for-loop");
                System.out.println("emailid is:"+emailid+"userid is:"+id);
            }
            favRecipesDao.deleteById(id);
        }
        else{
            throw new userNotFound("User not found.");
        }
    }
}
