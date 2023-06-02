package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.Exceptions.IngredientAlreadyExistsException;
import com.superchef.Super.Chef.Exceptions.IngredientNotFoundException;
import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.daos.IngredientDao;
import com.superchef.Super.Chef.daos.UserDao;
import com.superchef.Super.Chef.entities.Ingredients;
import com.superchef.Super.Chef.entities.User;
import com.superchef.Super.Chef.entities.UserIng;
import com.superchef.Super.Chef.entities.User_Fav_Recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class IngredientsServiceImpl implements IngredienstService {

    @Autowired
    IngredientDao ingDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserIng userIng;


    @Override
    public Ingredients addIngredients(Ingredients ingredients, String emailid) {

        Optional<User> result = userDao.findById(emailid);
        Ingredients ing = null;
        if (result.isPresent()) {
            User user = result.get();
            Set<UserIng> mappingusering = user.getUseringMapping();
            if (mappingusering != null) {
                for (UserIng mapping : mappingusering) {
                    if (mapping.getIng().getIngName().equalsIgnoreCase(ingredients.getIngName())) {
                        throw new IngredientAlreadyExistsException("Ingredients Already associated with user.");
                    }
                }
            }
            ing = ingDao.save(ingredients);
            userIng.setUser(user);
            userIng.setIng(ing);
            Set<UserIng> userIngSet = new HashSet<>();
            userIngSet.add(userIng);
            user.setUseringMapping(userIngSet);
            userDao.save(user);
        } else {
            throw new userNotFound("User Not Found!!");
        }
        return ing;
    }

    @Override
    public Set<Ingredients> getIngredientsByName(String emailid, String ingName) {
        Optional<User> result = userDao.findById(emailid);
        Set<Ingredients> ing = new HashSet<>();
        if (result.isPresent()) {
            User user = result.get();
            ing = new HashSet<>();
            if (user.getUseringMapping() != null) {
                for (UserIng mapping : user.getUseringMapping()) {
                    if (mapping.getIng().getIngName().equalsIgnoreCase(ingName)) {
                        ing.add(mapping.getIng());
                    }
                }
                if (ing.size() == 0) {
                    throw new IngredientNotFoundException("No Ingredients Found");
                }
            } else {
                throw new IngredientNotFoundException("No Ingredients Found");
            }
        }
        return ing;
    }

    @Override
    public Set<Ingredients> getIngredientsByCategory(String emailid, String ingCategory) {
        Optional<User> result = userDao.findById(emailid);
        Set<Ingredients> ing = new HashSet<>();
        if (result.isPresent()) {
            User user = result.get();
            ing = new HashSet<>();
            if (user.getUseringMapping() != null) {
                for (UserIng mapping : user.getUseringMapping()) {
                    if (mapping.getIng().getIngCategory().equalsIgnoreCase(ingCategory)) {
                        ing.add(mapping.getIng());
                    }
                }
                if (ing.size() == 0) {
                    throw new IngredientNotFoundException("No Ingredient Found");
                }
            } else {
                throw new IngredientNotFoundException("No Ingredients Found");
            }
        }
        return ing;

    }

    public Set<Ingredients> getAllIng(String emailid) {
        Optional<User> result = userDao.findById(emailid);
        Set<Ingredients> ingSet = new HashSet<>();
        if (result.isPresent()) {
            User user = result.get();
            if (user.getUseringMapping() != null) {
                for (UserIng mapping : user.getUseringMapping()) {
                    ingSet.add(mapping.getIng());
                }
            } else {
                throw new IngredientNotFoundException("No Ingredients Found");
            }
        } else {
            throw new userNotFound("User Not Found");
        }
        System.out.println(ingSet.toString());
        return ingSet;
    }

    public void deleteIngByName(String emailid, String ingname) {
        Optional<User> result = userDao.findById(emailid);
        if (result.isPresent()) {
            User user = result.get();
            int id = 0;
            for (UserIng mapping : user.getUseringMapping()) {
                if (mapping.getIng().getIngName().equalsIgnoreCase(ingname)) {
                    id = mapping.getIng().getIngId();
                    user.getUseringMapping().remove(mapping);
                    break;
                }
            }
            if (id == 0) {
                throw new IngredientNotFoundException("No Ingredient Found");
            }
            ingDao.deleteById(id);

        } else {
            throw new userNotFound("User Not Found.");
        }
    }

    public void deleteIngByCategory(String emailid, String ingCategory) {
        Optional<User> result = userDao.findById(emailid);
        if (result.isPresent()) {
            User user = result.get();
            Set<Integer> idSet = new HashSet<>();
            Set<Ingredients> ingSet = new HashSet<>();

            for (UserIng mapping : user.getUseringMapping()) {
                System.out.println(mapping.getIng().getIngCategory());
                if (mapping.getIng().getIngCategory().equalsIgnoreCase(ingCategory)) {
                    idSet.add(mapping.getIng().getIngId());
                    ingSet.add(mapping.getIng());

                }
            }

            if (idSet.size() == 0) {
                throw new IngredientNotFoundException("No Ingredient Found");
            }
            System.out.println(ingSet.size());
            System.out.println(idSet.size());
            System.out.println(user.getUseringMapping().removeAll(ingSet));
            ingDao.deleteAllById(idSet);

        } else {
            throw new userNotFound("User Not Found.");
        }
    }

    public void deleteAllIng(String emailid) {
        Optional<User> result = userDao.findById(emailid);
        if (result.isPresent()) {
            User user = result.get();
            Set<Integer> idSet = new HashSet<>();
            if (user.getUseringMapping() != null) {
                for (UserIng mapping : user.getUseringMapping()) {
                    idSet.add(mapping.getIng().getIngId());
                }
                user.getUseringMapping().clear();
                ingDao.deleteAllById(idSet);
            } else {
                throw new IngredientNotFoundException("Ingredients Not Found");
            }

        } else {
            throw new userNotFound("User Not Found.");
        }
    }

}
