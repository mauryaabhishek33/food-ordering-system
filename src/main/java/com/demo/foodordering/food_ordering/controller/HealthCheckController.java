package com.demo.foodordering.food_ordering.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    /**
     * A simple health check endpoint that returns a message indicating that the service is up.
     *
     * @return a message indicating that the service is up
     */
    @GetMapping("/")
    public String index() {
        return "Greetings from Food Bhandaraa";
    }
}
