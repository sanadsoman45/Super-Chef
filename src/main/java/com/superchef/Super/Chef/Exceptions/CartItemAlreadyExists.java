package com.superchef.Super.Chef.Exceptions;

public class CartItemAlreadyExists extends RuntimeException{
    public CartItemAlreadyExists(String message) {
        super(message);
    }

    public CartItemAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public CartItemAlreadyExists(Throwable cause) {
        super(cause);
    }
}
