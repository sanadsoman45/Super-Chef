package com.superchef.Super.Chef.Exceptions;

public class RecipeNotFound extends RuntimeException{

    public RecipeNotFound(String message) {
        super(message);
    }

    public RecipeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipeNotFound(Throwable cause) {
        super(cause);
    }
}
