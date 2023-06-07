package com.superchef.Super.Chef.services;

import com.superchef.Super.Chef.entities.Ingredients;

import java.util.Set;

public interface IngredienstService {

    Ingredients addIngredients(Ingredients ingredients, String emailid);

    Set<Ingredients> getIngredientsByName(String emailid, String ingName);

    Set<Ingredients> getIngredientsByCategory(String emailid, String ingCategory);

    Set<Ingredients> getAllIng(String emailid);

    void deleteIngByName(String emailid, String ingname);

    void deleteIngByCategory(String emailid, String ingCategory);

    void deleteAllIng(String emailid);

}
