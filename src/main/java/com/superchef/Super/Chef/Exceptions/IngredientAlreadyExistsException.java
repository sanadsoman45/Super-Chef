package com.superchef.Super.Chef.Exceptions;

public class IngredientAlreadyExistsException extends RuntimeException{

    public IngredientAlreadyExistsException(String message) {
        super(message);
    }

    public IngredientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
