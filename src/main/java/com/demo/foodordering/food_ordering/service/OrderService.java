package com.demo.foodordering.food_ordering.service;

import com.demo.foodordering.food_ordering.domain.Order;
import com.demo.foodordering.food_ordering.dto.CustomerOrderDto;
import com.demo.foodordering.food_ordering.exception.FoodOrderingException;

import java.util.List;

// OrderService.java
public interface OrderService {
    Order placeOrder(Long id , List<CustomerOrderDto> items) throws FoodOrderingException;
}
