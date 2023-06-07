package com.superchef.Super.Chef.controllers;

import com.superchef.Super.Chef.Exceptions.IngredientAlreadyExistsException;
import com.superchef.Super.Chef.Exceptions.IngredientNotFoundException;
import com.superchef.Super.Chef.Exceptions.userNotFound;
import com.superchef.Super.Chef.entities.Ingredients;
import com.superchef.Super.Chef.entities.User;
import com.superchef.Super.Chef.entities.UserIng;
import com.superchef.Super.Chef.services.IngredienstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/superchef")
public class IngredientController {

    @Autowired
    private IngredienstService ingService;

    @PostMapping("/ing/{emailid}")
    public Ingredients addIngredients(@RequestBody Ingredients ingredients,@PathVariable String emailid) {
        return ingService.addIngredients(ingredients,emailid);
    }


    @GetMapping("/user/{emailid}/ing/{ingname}")
    public Set<Ingredients> getIngredientsByName(@PathVariable String emailid,@PathVariable String ingname) {
        return ingService.getIngredientsByName(emailid,ingname);
    }

    @GetMapping("/user/{emailid}/ingcat/{ingcategory}")
    public Set<Ingredients> getIngredientsByCategory(@PathVariable String emailid,@PathVariable String ingcategory) {
        return ingService.getIngredientsByCategory(emailid,ingcategory);
    }

    @GetMapping("/user/ing/{emailid}")
    public Set<Ingredients> getAllIng(@PathVariable String emailid) {
        return ingService.getAllIng(emailid);

    }

    @DeleteMapping("/user/{emailid}/ing/{ingname}")
    public void deleteIngByName(@PathVariable  String emailid,@PathVariable String ingname) {
        ingService.deleteIngByName(emailid,ingname);
    }

    @DeleteMapping("/user/{emailid}/ingcat/{ingcategory}")
    public void deleteIngByCategory(@PathVariable String emailid,@PathVariable String ingcategory) {
        ingService.deleteIngByCategory(emailid, ingcategory);
    }

    @DeleteMapping("/ing/user/{emailid}")
    public void deleteAllIng(@PathVariable String emailid) {
        ingService.deleteAllIng(emailid);
    }

}
