package com.superchef.Super.Chef.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipes {

    //define the fields

    @Id
    @Column(name = "recipe_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @Column(name = "cook_time",nullable = false)
    private String cook_time;

    @Column(name = "image_url",nullable = false)
    @Type(type = "text")
    private String imageUrl;

    @Column(name="prep_time",nullable = false)
    @Type(type = "text")
    private String prepTime;

    @Column(name = "recipe_ing", nullable = false)
    @Type(type = "text")
    private String recipeIng;

    @Column(name = "recipe_instr", nullable = false)
    @Type(type = "text")
    private String recipeInstr;

    @Column(name = "recipe_name",nullable = false, length = 200)
    private String recipeName;

    @Column(name = "total_time",length = 100, nullable = false)
    private String total_time;



    //define the getters and setters


    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getCook_time() {
        return cook_time;
    }

    public void setCook_time(String cook_time) {
        this.cook_time = cook_time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getRecipeIng() {
        return recipeIng;
    }

    public void setRecipeIng(String recipeIng) {
        this.recipeIng = recipeIng;
    }

    public String getRecipeInstr() {
        return recipeInstr;
    }

    public void setRecipeInstr(String recipeInstr) {
        this.recipeInstr = recipeInstr;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    //toString


    @Override
    public String toString() {
        return "Recipes{" +
                "recipeId=" + recipeId +
                ", cook_time='" + cook_time + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", prepTime='" + prepTime + '\'' +
                ", recipeIng='" + recipeIng + '\'' +
                ", recipeInstr='" + recipeInstr + '\'' +
                ", recipeName='" + recipeName + '\'' +
                ", total_time='" + total_time + '\'' +
                '}';
    }
}
