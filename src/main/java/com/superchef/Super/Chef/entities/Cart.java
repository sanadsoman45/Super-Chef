package com.superchef.Super.Chef.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Component
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ingName;

    private String ingType;

    @OneToMany(mappedBy = "cart",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    private Set<IngCart> ingCart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public String getIngType() {
        return ingType;
    }

    public void setIngType(String ingType) {
        this.ingType = ingType;
    }

    public Set<IngCart> getIngCart() {
        return ingCart;
    }

    public void setIngCart(Set<IngCart> ingCart) {
        this.ingCart = ingCart;
    }
}
