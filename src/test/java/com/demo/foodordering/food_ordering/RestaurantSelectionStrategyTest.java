package com.demo.foodordering.food_ordering;

import com.demo.foodordering.food_ordering.service.RestaurantSelectionStrategy;
import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.demo.foodordering.food_ordering.dto.CustomerOrderDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class RestaurantSelectionStrategyTest {

    @InjectMocks
    private RestaurantSelectionStrategy strategy;

    @Test
    void testRestaurantSelectionStrategyNotNull() {
        assertNotNull(strategy);
    }

    @Test
    void testRestaurantSelectionStrategyReturnsValidRestaurant() {
        // assuming CustomerOrderDto is the input for the strategy
        CustomerOrderDto orderDto = new CustomerOrderDto();
        Restaurant restaurant = strategy.selectRestaurant(orderDto);
        assertNotNull(restaurant);
    }

    @Test
    void testRestaurantSelectionStrategyThrowsExceptionForInvalidInput() {
        // assuming null is an invalid input for the strategy
        assertThrows(Exception.class, () -> strategy.selectRestaurant(null));
    }
}