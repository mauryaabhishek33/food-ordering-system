package com.demo.foodordering.food_ordering.config;

import com.demo.foodordering.food_ordering.service.LowestCostStrategy;
import com.demo.foodordering.food_ordering.service.RestaurantSelectionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StrategyConfig {

    /**
     * This method is used to define the primary bean for the RestaurantSelectionStrategy interface.
     * It creates an instance of the LowestCostStrategy class and returns it as a bean.
     *
     * @return RestaurantSelectionStrategy instance of LowestCostStrategy
     */
    @Bean
    @Primary
    public RestaurantSelectionStrategy restaurantSelectionStrategy() {
        // Creating an instance of the LowestCostStrategy class
        return new LowestCostStrategy();
    }

    public Object getDefaultValue() {
        return "defaultValue";
    }
}
