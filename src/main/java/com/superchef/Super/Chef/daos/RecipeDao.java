package com.superchef.Super.Chef.daos;


import com.superchef.Super.Chef.entities.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeDao extends JpaRepository<Recipes,Integer> {

    Recipes findByrecipeName(String recipename);

}
