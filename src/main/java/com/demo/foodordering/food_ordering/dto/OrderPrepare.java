package com.demo.foodordering.food_ordering.dto;

import com.demo.foodordering.food_ordering.domain.Restaurant;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderPrepare {

    Restaurant restaurant;

    int preparationTime;

    int quantity;
}