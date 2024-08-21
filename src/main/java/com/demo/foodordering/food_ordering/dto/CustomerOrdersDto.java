package com.demo.foodordering.food_ordering.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CustomerOrdersDto {

    List<CustomerOrderDto> orderDtoList;

}
