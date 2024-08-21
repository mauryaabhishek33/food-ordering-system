package com.demo.foodordering.food_ordering.exception;

public class FoodOrderingException extends Exception {

    public FoodOrderingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodOrderingException (String message) {
        super(message);
    }
}
