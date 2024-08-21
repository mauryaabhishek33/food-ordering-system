package com.demo.foodordering.food_ordering.dto;

import com.demo.foodordering.food_ordering.domain.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class OrderItemDto {

    @JsonProperty("id")
    private Long id;

    @Setter
    @JsonIgnore
    private Order order;

    @JsonProperty("res_id")
    private Long resutaurantId;

    @JsonProperty("item_id")
    private Long itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("qty")
    private int quantity;


}
