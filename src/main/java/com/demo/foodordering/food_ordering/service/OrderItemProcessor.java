package com.demo.foodordering.food_ordering.service;

import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.demo.foodordering.food_ordering.dto.OrderPrepare;
import com.demo.foodordering.food_ordering.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Component
@EnableAsync
public class OrderItemProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger("OrderItemProcessor");

    @Autowired
    RestaurantRepository restaurantRepository;

    private final Lock capacityLock = new ReentrantLock();

    @Async("threadPoolExecutor")
    public CompletableFuture<Boolean> processRetaurantOrder(OrderPrepare order) {
        try {
            if (reserveCapacity(order.getRestaurant(), order.getQuantity())) {
                LOGGER.info("Processing order for {}", order.getRestaurant().getName());
                process(order, order.getRestaurant());
                LOGGER.info("Order dispatched from {}", order.getRestaurant().getName());
                return new AsyncResult<>(true).completable();
            } else {
                LOGGER.warn("No capacity available at {}", order.getRestaurant().getName());
                return new AsyncResult<>(false).completable();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Order processing interrupted.");
            return new AsyncResult<>(false).completable();
        }
    }

    private boolean reserveCapacity(Restaurant restaurant, int quantity) {
        Restaurant.RestaurantBuilder builder = Restaurant.builder();
        capacityLock.lock();
        try {
            if (restaurant.getCurrentCapacity() + quantity <= restaurant.getMaxCapacity()) {
               Restaurant updatedRestaurant =  builder.id(restaurant.getId()).currentCapacity(restaurant.getCurrentCapacity()+quantity)
                       .maxCapacity(restaurant.getMaxCapacity()).name(restaurant.getName())
                       .menuItems(restaurant.getMenuItems()).ratingId(restaurant.getRatingId()).build();
                restaurantRepository.save(updatedRestaurant);
                return true;
            }
            return false;
        } finally {
            capacityLock.unlock();
        }
    }


    private void releaseCapacity(Restaurant restaurant, int quantity) {
        Restaurant.RestaurantBuilder builder = Restaurant.builder();
        capacityLock.lock();
        try {
            Restaurant restaurantDetailsFromDB = restaurantRepository.findById(restaurant.getId()).get();
            Restaurant updatedRestaurant =  builder.id(restaurantDetailsFromDB.getId()).currentCapacity(restaurantDetailsFromDB.getCurrentCapacity()-quantity)
                    .maxCapacity(restaurantDetailsFromDB.getMaxCapacity()).name(restaurantDetailsFromDB.getName())
                    .menuItems(restaurantDetailsFromDB.getMenuItems()).ratingId(restaurantDetailsFromDB.getRatingId()).build();
            restaurantRepository.save(updatedRestaurant);
        } finally {
            capacityLock.unlock();
        }
    }

    public void process(OrderPrepare orderPrepare, Restaurant restaurant) throws InterruptedException {
        // Simulate food preparation time
        Thread.sleep(orderPrepare.getPreparationTime());
        releaseCapacity(restaurant, orderPrepare.getQuantity());
        // Release the capacity after preparation
    }

}

