package com.demo.foodordering.food_ordering.controller;

import com.demo.foodordering.food_ordering.domain.Order;
import com.demo.foodordering.food_ordering.dto.CustomerOrderDto;
import com.demo.foodordering.food_ordering.exception.FoodOrderingException;
import com.demo.foodordering.food_ordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Places an order for the given customer
     *
     * @param id The ID of the customer
     * @param items The items to be ordered
     * @return The placed order
     * @throws FoodOrderingException If there is an error processing the order
     */
    @PostMapping("/api/{id}/orders")
    public Order placeOrder(@PathVariable Long id, @RequestBody List<CustomerOrderDto> items) throws FoodOrderingException {
        return orderService.placeOrder(id, items);
    }
}
