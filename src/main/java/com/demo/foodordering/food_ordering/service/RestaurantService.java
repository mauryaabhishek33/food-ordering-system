package com.demo.foodordering.food_ordering.service;

import com.demo.foodordering.food_ordering.domain.MenuItem;
import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.demo.foodordering.food_ordering.dto.MenuItemDto;
import com.demo.foodordering.food_ordering.dto.RestaurantDto;
import com.demo.foodordering.food_ordering.exception.FoodOrderingException;

import java.util.List;

public interface RestaurantService {

    void registerRestaurant(RestaurantDto restaurant);
    void upsertMenuItem(Long restaurantId, List<MenuItemDto> menu) throws FoodOrderingException;
    RestaurantDto retrieveRestaurantDetails(Long id);
    List<Restaurant> getAllRestaurants();
    List<MenuItem> getAllItems();
    void updateCapacity(Long restaurantId, int capacityChange);
}
