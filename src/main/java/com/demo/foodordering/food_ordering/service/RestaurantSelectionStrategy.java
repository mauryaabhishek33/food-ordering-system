package com.demo.foodordering.food_ordering.service;

import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.demo.foodordering.food_ordering.dto.CustomerOrderDto;

public interface RestaurantSelectionStrategy {

    /**
     * This method selects a restaurant based on the customer order.
     *
     * @param customerOrderDto the customer order details
     * @return the selected restaurant
     */
    Restaurant selectRestaurant(CustomerOrderDto customerOrderDto);

    /**
     * Selects a restaurant for the given customer order.
     *
     * @param customerOrderDto the customer order details
     * @return the selected restaurant
     */

    // This method selects a restaurant based on the customer order.
    // It takes a CustomerOrderDto object as a parameter, which contains the details of the customer order.
    // The method returns a Restaurant object, which represents the selected restaurant.
}
