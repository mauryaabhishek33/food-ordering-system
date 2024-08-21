package com.demo.foodordering.food_ordering.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CustomerOrderDto {

    @JsonProperty("id")
    Long itemId;

    @JsonProperty("name")
    String itemName;

    @JsonProperty("quantity")
    int quantity;

}
