package com.superchef.Super.Chef.Exceptions;

public class IngredientNotFoundException extends RuntimeException{

    public IngredientNotFoundException(String message) {
        super(message);
    }

    public IngredientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientNotFoundException(Throwable cause) {
        super(cause);
    }
}
