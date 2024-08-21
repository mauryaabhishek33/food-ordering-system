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
public class RestaurantDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("rating_id")
    private int ratingId;

    @JsonProperty("maxCapacity")
    private Integer maxCapacity;

    @JsonProperty("currentCapacity")
    private Integer currentCapacity;



}
