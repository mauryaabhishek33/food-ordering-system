package com.demo.foodordering.food_ordering.service;

import com.demo.foodordering.food_ordering.domain.MenuItem;
import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.demo.foodordering.food_ordering.dto.CustomerOrderDto;
import com.demo.foodordering.food_ordering.repository.MenuItemRepository;
import com.demo.foodordering.food_ordering.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LowestCostStrategy implements RestaurantSelectionStrategy {

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    RestaurantRepository restaurantRepository;


    /**
     * Select a restaurant based on the lowest cost strategy
     * @param customerOrder the order to be processed
     * @return the selected restaurant
     */
    @Override
    public Restaurant selectRestaurant(CustomerOrderDto customerOrder) {
        Restaurant.RestaurantBuilder builder = Restaurant.builder();

        // Get all menu items with the given itemId
        List<MenuItem> availableMenuItems = menuItemRepository.findByItemIdEquals(customerOrder.getItemId());

        // Get the list of restaurant ids from the available menu items
        List<Long> restaurantIds = availableMenuItems.stream().map(k-> k.getRestaurantId()).collect(Collectors.toList());

        // Find the menu item with the lowest price that has enough capacity to fulfill the order
        MenuItem menuItem1 = availableMenuItems.stream()
                .filter(r->r.getRestaurant()!=null && r.getRestaurant().getCurrentCapacity() + customerOrder.getQuantity()<=r.getRestaurant().getMaxCapacity())
                .min(Comparator.comparing(MenuItem::getPrice)).get();

        // Build the selected restaurant
        availableMenuItems.stream()
                .filter(v-> v.getRestaurant()!=null)
                .filter(r -> r.getRestaurant().getCurrentCapacity() + customerOrder.getQuantity()<=r.getRestaurant().getMaxCapacity())
                .min(Comparator.comparing(MenuItem::getPrice))
                .ifPresent(menuItem -> builder.id(menuItem.getRestaurantId())
                        .currentCapacity(menuItem.getRestaurant().getCurrentCapacity())
                        .name(menuItem.getRestaurant().getName())
                        .maxCapacity(menuItem.getRestaurant().getMaxCapacity())
                        .ratingId(menuItem.getRestaurant().getRatingId())
                        .build());
        return builder.build();
    }
}
