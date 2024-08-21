//package com.demo.foodordering.food_ordering.domain;
//
//import com.demo.foodordering.food_ordering.dto.ItemDto;
//import com.demo.foodordering.food_ordering.dto.MenuItemDto;
//import com.demo.foodordering.food_ordering.dto.RestaurantDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MapStructMapperImpl implements MapStructMapper {
//
//    @Override
//    public MenuItemDto menuToMenuItemDto(MenuItem menuItem) {
//        if ( menuItem == null ) {
//            return null;
//        }
//
//        MenuItemDto menuItemDto =  MenuItemDto.builder()
//                .id(menuItem.getId())
//                .itemId(menuItem.getItemId())
//                .restaurantId(menuItem.getRestaurantId())
//                .price(menuItem.getPrice())
//                .build();
//
//        return menuItemDto;
//    }
//
//    @Override
//    public MenuItem toMenuItemDomain(MenuItemDto menuItemDto) {
//        if ( menuItemDto == null ) {
//            return null;
//        }
//
//        MenuItem menuItem = MenuItem.builder()
//                .id(menuItemDto.getId())
//                .itemId(menuItemDto.getItemId())
//                .price(menuItemDto.getPrice())
//                .restaurantId(menuItemDto.getRestaurant().getId())
//                .item(menuItemDto.getItem())
//                .restaurant(menuItemDto.getRestaurant())
//                .build();
//
//        return menuItem;
//    }
//
//    @Override
//    public RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
//        if ( restaurant == null ) {
//            return null;
//        }
//
//        RestaurantDto restaurantDto = RestaurantDto.builder()
//                .id(restaurant.getId())
//                .name(restaurant.getName())
//                .maxCapacity(restaurant.getMaxCapacity())
//                .currentCapacity(restaurant.getCurrentCapacity())
//                .ratingId(restaurant.getRatingId())
//                .build();
//
//        return restaurantDto;
//    }
//
//    @Override
//    public Restaurant toRestaurantDomain(RestaurantDto restaurantDto) {
//        if ( restaurantDto == null ) {
//            return null;
//        }
//
//        Restaurant restaurant = Restaurant.builder()
//                .id(restaurantDto.getId())
//                .name(restaurantDto.getName())
//                .ratingId(restaurantDto.getRatingId())
//                .maxCapacity(restaurantDto.getMaxCapacity())
//                .currentCapacity(restaurantDto.getMaxCapacity())
//                .build();
//        return restaurant;
//    }
//
//    @Override
//    public Item toItemDomain(ItemDto itemDto) {
//        if (itemDto == null) {
//            return null;
//        }
//        Item item = Item.builder()
//                .id(itemDto.getId())
//                .name(itemDto.getName()).build();
//        return item;
//    }
//
//    @Override
//    public ItemDto toItemDto(Item item) {
//        if ( item == null ) {
//            return null;
//        }
//
//        ItemDto itemDto = ItemDto.builder()
//                .id(item.getId())
//                .name(item.getName()).build();
//        return itemDto;
//    }
//
//    public MenuItem fromDtoToMenuItemDomain(MenuItemDto menuItemDto, MenuItem item) {
//        if ( menuItemDto == null ) {
//            return null;
//        }
//        MenuItem.MenuItemBuilder builder = MenuItem.builder();
//        if (menuItemDto.getItem()!=item.getItem()) {
//            builder.item(menuItemDto.getItem());
//        }
//        MenuItem menuItem = MenuItem.builder()
//                .id(menuItemDto.getId())
//                .itemId(menuItemDto.getItemId())
//                .price(menuItemDto.getPrice())
//                .restaurantId(menuItemDto.getRestaurantId())
//                .item(menuItemDto.getItem())
//                .restaurant(menuItemDto.getRestaurant())
//                .build();
//
//        return menuItem;
//    }
//
//}
//
