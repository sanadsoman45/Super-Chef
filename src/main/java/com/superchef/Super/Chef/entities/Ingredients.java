package com.superchef.Super.Chef.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
public class Ingredients{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingId;

    private String ingName;

    private String ingCategory;

    @OneToMany(mappedBy = "ing",cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.LAZY)
    private Set<UserIng> ingMapping = new HashSet<>();

    public Set<UserIng> getIngMapping() {
        return ingMapping;
    }

    public void setIngMapping(Set<UserIng> ingMapping) {
        this.ingMapping = ingMapping;
    }

    public int getIngId() {
        return ingId;
    }

    public void setIngId(int ingId) {
        this.ingId = ingId;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public String getIngCategory() {
        return ingCategory;
    }

    public void setIngCategory(String ingCategory) {
        this.ingCategory = ingCategory;
    }
}
