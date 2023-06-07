package com.superchef.Super.Chef.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class UserIng {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade =  {CascadeType.PERSIST,CascadeType.REMOVE})
    @JsonIgnore
    @JoinColumn(name = "ing_id")
    private Ingredients ing;

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

    public Ingredients getIng() {
        return ing;
    }

    public void setIng(Ingredients ing) {
        this.ing = ing;
    }
}
