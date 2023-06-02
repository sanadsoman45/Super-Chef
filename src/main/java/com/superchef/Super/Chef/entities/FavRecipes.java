package com.superchef.Super.Chef.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name="Fav_Recipes")
public class FavRecipes {

    //define the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favrecipeId", length = 100)
    private int favrecipeId;
    @Column(name = "favrecipeName", length = 200)
    private String favrecipeName;
    @Column(name = "favimageUrl", length = 65535, columnDefinition = "TEXT")
    @Type(type = "text")
    private String favimageUrl;
    @Column(name = "favrecipeIng", length = 65535, columnDefinition = "TEXT")
    @Type(type = "text")
    private String favrecipeIng;
    @Column(name = "favrecipeInstr", length = 65535, columnDefinition = "TEXT")
    @Type(type = "text")
    private String favrecipeInstr;
    @Column(name = "favcookTime", length = 100)
    private String favcookTime;
    @Column(name = "favprepTime", length = 100)
    private String favprepTime;
    @Column(name = "favtotalTime", length = 100)
    private String favtotalTime;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},mappedBy = "favRecipes",fetch = FetchType.LAZY)
    private Set<User_Fav_Recipes> favMappings = new HashSet<>();


    //defining the getters and setters.
    public int getFavrecipeId() {
        return favrecipeId;
    }

    public void setFavrecipeId(int favrecipeId) {
        this.favrecipeId = favrecipeId;
    }

    public String getFavrecipeName() {
        return favrecipeName;
    }

    public void setFavrecipeName(String favrecipeName) {
        this.favrecipeName = favrecipeName;
    }

    public String getFavimageUrl() {
        return favimageUrl;
    }

    public void setFavimageUrl(String favimageUrl) {
        this.favimageUrl = favimageUrl;
    }

    public String getFavrecipeIng() {
        return favrecipeIng;
    }

    public void setFavrecipeIng(String favrecipeIng) {
        this.favrecipeIng = favrecipeIng;
    }

    public String getFavrecipeInstr() {
        return favrecipeInstr;
    }

    public void setFavrecipeInstr(String favrecipeInstr) {
        this.favrecipeInstr = favrecipeInstr;
    }

    public String getFavcookTime() {
        return favcookTime;
    }

    public void setFavcookTime(String favcookTime) {
        this.favcookTime = favcookTime;
    }

    public String getFavprepTime() {
        return favprepTime;
    }

    public void setFavprepTime(String favprepTime) {
        this.favprepTime = favprepTime;
    }

    public String getFavtotalTime() {
        return favtotalTime;
    }

    public void setFavtotalTime(String favtotalTime) {
        this.favtotalTime = favtotalTime;
    }

    public Set<User_Fav_Recipes> getFavMappings() {
        return favMappings;
    }

    public void setFavMappings(Set<User_Fav_Recipes> favMappings) {
        this.favMappings = favMappings;
    }


}
