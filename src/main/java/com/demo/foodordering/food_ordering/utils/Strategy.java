package com.demo.foodordering.food_ordering.utils;

public enum Strategy {

    LOW_COST("LC"),
    RATING("R");

    String value;

    Strategy(String value) {
        this.value = value;
    }
}
