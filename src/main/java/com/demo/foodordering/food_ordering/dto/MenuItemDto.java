package com.demo.foodordering.food_ordering.dto;


import com.demo.foodordering.food_ordering.domain.Item;
import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MenuItemDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("res_id")
    @JsonIgnore
    private Long restaurantId;

    @JsonIgnore
    @Setter
    private Long itemId;

    @JsonProperty("name")
    @Setter
    private String itemName;

    @Setter
    private Item item;

    @Setter
    private Restaurant restaurant;

    @JsonProperty("price")
    private double price;


    public void applyMenuItem(MenuItemDto menuItem) {
        if (id==0) {
            this.id= menuItem.getId();
        }

        if (itemName==null) {
            this.itemName=menuItem.getItemName();
        }

        if (restaurantId==0) {
            this.restaurantId = menuItem.getRestaurantId();
        }

        if (itemId==0) {
            this.itemId = menuItem.getItemId();
        }

    }

}
