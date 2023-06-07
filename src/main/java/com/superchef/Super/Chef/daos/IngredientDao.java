package com.superchef.Super.Chef.daos;

import com.superchef.Super.Chef.entities.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientDao extends JpaRepository<Ingredients, Integer> {



}
