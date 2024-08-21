package com.demo.foodordering.food_ordering.controller;

import com.demo.foodordering.food_ordering.domain.Order;
import com.demo.foodordering.food_ordering.dto.CustomerOrderDto;
import com.demo.foodordering.food_ordering.exception.FoodOrderingException;
import com.demo.foodordering.food_ordering.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void testPlaceOrder_Successful() throws Exception {
        // Arrange
        Long customerId = 1L;
        List<CustomerOrderDto> items = Arrays.asList(new CustomerOrderDto());
        Order expectedOrder = new Order();
        when(orderService.placeOrder(customerId, items)).thenReturn(expectedOrder);

        // Act and Assert
        mockMvc.perform(post("/api/{id}/orders", customerId)
                        .contentType("application/json")
                        .content(asJsonString(items)))
                .andExpect(status().isOk());
    }

    @Test
    public void testPlaceOrder_InvalidCustomerId() throws Exception {
        // Arrange
        Long customerId = null;
        List<CustomerOrderDto> items = Arrays.asList(new CustomerOrderDto());

        // Act and Assert
        mockMvc.perform(post("/api/{id}/orders", customerId)
                        .contentType("application/json")
                        .content(asJsonString(items)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPlaceOrder_EmptyItemsList() throws Exception {
        // Arrange
        Long customerId = 1L;
        List<CustomerOrderDto> items = Arrays.asList();

        // Act and Assert
        mockMvc.perform(post("/api/{id}/orders", customerId)
                        .contentType("application/json")
                        .content(asJsonString(items)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPlaceOrder_NullItemsList() throws Exception {
        // Arrange
        Long customerId = 1L;
        List<CustomerOrderDto> items = null;

        // Act and Assert
        mockMvc.perform(post("/api/{id}/orders", customerId)
                        .contentType("application/json")
                        .content(asJsonString(items)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPlaceOrder_FoodOrderingExceptionThrown() throws Exception {
        // Arrange
        Long customerId = 1L;
        List<CustomerOrderDto> items = Arrays.asList(new CustomerOrderDto());
        when(orderService.placeOrder(customerId, items)).thenThrow(FoodOrderingException.class);

        // Act and Assert
        mockMvc.perform(post("/api/{id}/orders", customerId)
                        .contentType("application/json")
                        .content(asJsonString(items)))
                .andExpect(status().isInternalServerError());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}