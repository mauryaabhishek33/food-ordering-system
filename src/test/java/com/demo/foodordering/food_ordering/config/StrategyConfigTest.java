package com.demo.foodordering.food_ordering.config;

import com.demo.foodordering.food_ordering.service.RestaurantSelectionStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StrategyConfigTest {

    @Test
    void testStrategyConfigNotNull() {
        StrategyConfig config = new StrategyConfig();
        assertNotNull(config);
    }

    @Test
    void testStrategyConfigReturnsValidRestaurant() {
        StrategyConfig config = new StrategyConfig();
        RestaurantSelectionStrategy strategy = config.restaurantSelectionStrategy();
        assertNotNull(strategy);
    }


}