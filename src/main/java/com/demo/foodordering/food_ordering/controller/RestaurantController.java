package com.demo.foodordering.food_ordering.controller;

import com.demo.foodordering.food_ordering.dto.MenuItemDto;
import com.demo.foodordering.food_ordering.dto.RestaurantApiResponse;
import com.demo.foodordering.food_ordering.dto.RestaurantDto;
import com.demo.foodordering.food_ordering.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RestaurantController {

    private static final Logger LOGGER = LoggerFactory.getLogger("RestaurantController");

    @Autowired
    RestaurantService restaurantService;


    /**
     * Registers a new restaurant with the provided details.
     *
     * @param restaurantRequest the details of the restaurant to register.
     * @return a {@link ResponseEntity} containing a {@link RestaurantApiResponse} with the status, message, and name of the registered restaurant.
     */
    @PostMapping("/api/restaurant")
    public ResponseEntity<RestaurantApiResponse<String>> register(@RequestBody() RestaurantDto restaurantRequest) {
        try {
            // Register the new restaurant
            restaurantService.registerRestaurant(restaurantRequest);

            // Create a success response with the status, message, and name of the registered restaurant
            return ResponseEntity.ok(new RestaurantApiResponse<>(HttpStatus.OK.value(),
                    " successfully registered", restaurantRequest.getName()));
        } catch (Exception e) {
            // Log the error and create an error response with the status, message, and name of the failed registration
            LOGGER.error("exception while registering new restaurant with error : {} ", e.getMessage());
            return ResponseEntity.internalServerError().body(new RestaurantApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    " registration failed due to error ", restaurantRequest.getName()));
        }
    }

    /**
     * Retrieves the details of the restaurant with the given id.
     *
     * @param id the id of the restaurant to retrieve
     * @return a ResponseEntity containing a RestaurantApiResponse with the status, message, and retrieved restaurant details
     */
    @GetMapping("/api/restaurant")
    public ResponseEntity<RestaurantApiResponse<RestaurantDto>> retrieveRestaurantDetails(@RequestParam("id") long id) {
        try {
            // Try to retrieve the restaurant with the given id
            RestaurantDto restaurant = restaurantService.retrieveRestaurantDetails(id);

            // If the restaurant doesn't exist, return a 404 response
            if (restaurant == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestaurantApiResponse<>(HttpStatus.NOT_FOUND.value(), "Restaurant doesn't not exist with id " + id, null));
            } else {
                // Return a 200 response with the retrieved restaurant details
                return ResponseEntity.ok(new RestaurantApiResponse<>(HttpStatus.OK.value(), "Restaurant details retrieved sucessfully" , restaurant));
            }
        } catch (Exception e) {
            // Log the error and return a 500 response
            LOGGER.error("exception while finding restaurant with error : {} ", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Updates the menu for the given restaurant id.
     *
     * @param id the id of the restaurant to update
     * @param menuItemRequest the list of menu items to update
     * @return a ResponseEntity with the status, message, and id of the restaurant
     */
    @PutMapping("/api/restaurant/menu")
    public ResponseEntity<RestaurantApiResponse<Long>> updateMenu(@RequestParam("id") Long id, @RequestBody List<MenuItemDto> menuItemRequest) {
        try {
            // Update the menu for the given restaurant id
            restaurantService.upsertMenuItem(id, menuItemRequest);

            // Create a success response with the status, message, and id of the restaurant
            return ResponseEntity.ok(new RestaurantApiResponse<>(HttpStatus.OK.value(),
                    " successfully registered menu for restaurant id " + id, id));
        } catch (Exception e) {
            // Log the error and create an error response with the status, message, and id of the restaurant
            LOGGER.error("exception while updating menu with error : {} ", e.getMessage());
            return ResponseEntity.internalServerError().body(new RestaurantApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    " registration failed due to error for restaurant id ", id));
        }
    }


}
