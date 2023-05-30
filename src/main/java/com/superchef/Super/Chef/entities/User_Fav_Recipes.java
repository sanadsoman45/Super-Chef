package com.superchef.Super.Chef.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name="userFav")
public class User_Fav_Recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="favrecipe_id")
    private FavRecipes favRecipes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FavRecipes getFavRecipes() {
        return favRecipes;
    }

    public void setFavRecipes(FavRecipes favRecipes) {
        this.favRecipes = favRecipes;
    }


}
