package com.demo.foodordering.food_ordering.service;

import com.demo.foodordering.food_ordering.domain.Item;
import com.demo.foodordering.food_ordering.domain.MapStructMapper;
import com.demo.foodordering.food_ordering.domain.MenuItem;
import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.demo.foodordering.food_ordering.dto.MenuItemDto;
import com.demo.foodordering.food_ordering.dto.RestaurantDto;
import com.demo.foodordering.food_ordering.exception.FoodOrderingException;
import com.demo.foodordering.food_ordering.repository.ItemRepository;
import com.demo.foodordering.food_ordering.repository.MenuItemRepository;
import com.demo.foodordering.food_ordering.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private MapStructMapper mapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    ItemRepository itemRepository;


    public void registerRestaurant(RestaurantDto restaurantDto) {
         Restaurant restaurant =  mapper.toRestaurantDomain(restaurantDto);
         restaurantRepository.save(restaurant);
    }

    public RestaurantDto retrieveRestaurantDetails(Long id) {
       Optional<Restaurant> restaurantOptional =  restaurantRepository.findById(id);
       return mapper.restaurantToRestaurantDto(restaurantOptional.isEmpty()?null: restaurantOptional.get());
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }

    @Override
    public List<MenuItem> getAllItems() {
        return List.of();
    }


    @Override
    public void upsertMenuItem(Long restaurantId, List<MenuItemDto> menus) throws FoodOrderingException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isEmpty()) {
            throw new FoodOrderingException("Restaurant Do not exist, please register first", new EntityNotFoundException());
        }
        for (MenuItemDto dto : menus) {
            Optional<Item> item = itemRepository.findByNameEqualsIgnoreCase(dto.getItem().getName());
            if (item.isPresent()) {
                dto.setItemId(item.get().getId());
                dto.setItem(item.get());
                dto.setItemName(item.get().getName());
            } else {
                itemRepository.save(dto.getItem());
            }
            MenuItem menuItem = restaurant.get().getMenuItems().stream()
                    .filter(i->i.getItem().getName().equals(dto.getItemName()))
                    .findFirst()
                    .orElse(mapper.toMenuItemDomain(dto));
            menuItem.setPrice(dto.getPrice());
            menuItem.setRestaurantId(restaurantId);
            menuItem.setRestaurant(restaurant.get());
            menuItemRepository.save(menuItem);
        }
    }

    @Override
    public void updateCapacity(Long restaurantId, int capacityChange) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setCurrentCapacity(restaurant.getCurrentCapacity() + capacityChange);
        restaurantRepository.save(restaurant);
    }
}
