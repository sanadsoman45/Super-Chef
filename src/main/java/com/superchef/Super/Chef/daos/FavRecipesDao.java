package com.superchef.Super.Chef.daos;

import com.superchef.Super.Chef.entities.FavRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FavRecipesDao extends JpaRepository<FavRecipes,Integer> {
        Set<FavRecipes> findByfavrecipeName(String favrecipe_name);

}
