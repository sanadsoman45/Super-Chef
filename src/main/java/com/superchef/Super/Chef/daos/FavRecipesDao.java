package com.superchef.Super.Chef.daos;

import com.superchef.Super.Chef.entities.FavRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavRecipesDao extends JpaRepository<FavRecipes,Integer> {
        FavRecipes findByfavrecipeName(String favrecipe_name);

}
