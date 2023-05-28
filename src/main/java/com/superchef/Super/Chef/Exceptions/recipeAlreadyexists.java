package com.superchef.Super.Chef.Exceptions;

public class recipeAlreadyexists extends RuntimeException{

    public recipeAlreadyexists(String message) {
        super(message);
    }

    public recipeAlreadyexists(String message, Throwable cause) {
        super(message, cause);
    }

    public recipeAlreadyexists(Throwable cause) {
        super(cause);
    }
}
