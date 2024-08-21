package com.demo.foodordering.food_ordering.service;

import com.demo.foodordering.food_ordering.domain.MapStructMapper;
import com.demo.foodordering.food_ordering.domain.Order;
import com.demo.foodordering.food_ordering.domain.OrderItem;
import com.demo.foodordering.food_ordering.domain.Restaurant;
import com.demo.foodordering.food_ordering.dto.CustomerOrderDto;
import com.demo.foodordering.food_ordering.dto.OrderItemDto;
import com.demo.foodordering.food_ordering.dto.OrderPrepare;
import com.demo.foodordering.food_ordering.exception.FoodOrderingException;
import com.demo.foodordering.food_ordering.repository.*;
import com.demo.foodordering.food_ordering.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.Future;

@Service
@EnableAsync
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantSelectionStrategy selectionStrategy;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    MapStructMapper mapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    OrderItemProcessor orderItemProcessor;


    @Override
    public Order placeOrder(Long customerId, List<CustomerOrderDto> items) throws FoodOrderingException {
        Long orderId = generateOrderId(customerId);
        Order order = Order.builder().id(orderId).customerId(customerId).build();
       try {
           orderRepository.save(order);
           items.forEach(customerOrder -> {
               Restaurant selectedRestaurant = selectionStrategy.selectRestaurant(customerOrder);
               OrderItemDto orderItemDto = OrderItemDto.builder().order(order).orderId(orderId).quantity(customerOrder.getQuantity())
                       .resutaurantId(selectedRestaurant.getId()).itemId(customerOrder.getItemId()).itemName(customerOrder.getItemName()).build();
                 processOrderItem(customerId, customerOrder, selectedRestaurant);
               OrderItem orderItem = mapper.toOrderItem(orderItemDto);
               orderItemRepository.save(orderItem);
//               restaurantRepository.save(selectedRestaurant);
           });
           return orderRepository.save(order);
       } catch (Exception e) {
           // remove order from order table if exception occurs
           orderRepository.delete(order);
           System.out.println("order unsuccessful");
           throw new FoodOrderingException("order was unsuccessful", e);
       }
    }


    private Future<Boolean> processOrderItem(Long customerId, CustomerOrderDto customerOrder, Restaurant selectedRestaurant) {
        int preparationTime = Math.toIntExact(customerId)*Constants.PREP_TIME;
        OrderPrepare orderPrepare = OrderPrepare.builder().preparationTime(preparationTime)
                .quantity(customerOrder.getQuantity()).restaurant(selectedRestaurant).build();
        return orderItemProcessor.processRetaurantOrder(orderPrepare);
    }

    private Long generateOrderId(Long customerId) {
        try {
            long rndm = SecureRandom.getInstanceStrong().longs(Constants.RANDOM_NUMBER_ORIGIN, Constants.RANDOM_NUMBER_BOUND).findAny().getAsLong();
            return customerId+ rndm;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
