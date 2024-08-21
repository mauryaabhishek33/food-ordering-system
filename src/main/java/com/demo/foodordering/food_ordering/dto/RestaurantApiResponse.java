package com.demo.foodordering.food_ordering.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RestaurantApiResponse<T> {

    private Integer respCode;
    private String respMsg;

    private T obj;

}
