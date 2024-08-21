//package com.demo.foodordering.food_ordering.service;
//
//import com.demo.foodordering.food_ordering.domain.Item;
//import com.demo.foodordering.food_ordering.domain.MapStructMapper;
//import com.demo.foodordering.food_ordering.domain.MenuItem;
//import com.demo.foodordering.food_ordering.domain.Restaurant;
//import com.demo.foodordering.food_ordering.exception.FoodOrderingException;
//import com.demo.foodordering.food_ordering.dto.MenuItemDto;
//import com.demo.foodordering.food_ordering.repository.ItemRepository;
//import com.demo.foodordering.food_ordering.repository.MenuItemRepository;
//import com.demo.foodordering.food_ordering.repository.RestaurantRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class MenuItemService {
//
//    @Autowired
//    MenuItemRepository menuItemRepository;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    MapStructMapper mapper;
//
//    @Autowired
//    private RestaurantRepository restaurantRepository;
//
//    public void upsertMenuItem(MenuItemDto dto) throws FoodOrderingException {
//        Optional<Restaurant> restaurant = restaurantRepository.findById(dto.getRestaurant().getId());
//        if (restaurant.isEmpty()) {
//            throw new FoodOrderingException("Restaurant Do not exist, please register first", new EntityNotFoundException());
//        }
//        Optional<Item> item = itemRepository.findByNameEqualsIgnoreCase(dto.getItem().getName());
//        if (item.isPresent()) {
//            dto.setItemId(item.get().getId());
//            dto.setItem(item.get());
//            dto.setItemName(item.get().getName());
//        } else {
//            itemRepository.save(dto.getItem());
//        }
////        MenuItem menuItem = restaurant.get().getMenuItems().stream()
////                .filter(i->i.getItem().getName().equals(dto.getItemName()))
////                .findFirst()
////                .orElse(mapper.toMenuItemDomain(dto));
//        MenuItem menuItem =  mapper.toMenuItemDomain(dto);
//        menuItem.setPrice(dto.getPrice());
//        menuItemRepository.save(menuItem);
//    }
//
//}
