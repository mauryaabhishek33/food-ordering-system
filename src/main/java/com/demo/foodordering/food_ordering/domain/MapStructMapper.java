package com.demo.foodordering.food_ordering.domain;


import com.demo.foodordering.food_ordering.dto.ItemDto;
import com.demo.foodordering.food_ordering.dto.MenuItemDto;
import com.demo.foodordering.food_ordering.dto.OrderItemDto;
import com.demo.foodordering.food_ordering.dto.RestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    MenuItemDto menuToMenuItemDto(MenuItem menuItem);

    MenuItem toMenuItemDomain(MenuItemDto menuItemDto);

    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

    @Mapping(expression = "java(restaurantDto.getCurrentCapacity() != null ? restaurantDto.getCurrentCapacity(): restaurantDto.getMaxCapacity())", target = "currentCapacity")
    Restaurant toRestaurantDomain(RestaurantDto restaurantDto);

    Item toItemDomain(ItemDto itemDto);

    ItemDto toItemDto(Item item);

    OrderItem toOrderItem(OrderItemDto orderItemDto);

    OrderItemDto toOrderDto(OrderItem orderItem);

}
