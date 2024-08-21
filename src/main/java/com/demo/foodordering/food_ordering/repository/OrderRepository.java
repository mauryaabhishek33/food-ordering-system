package com.demo.foodordering.food_ordering.repository;

import com.demo.foodordering.food_ordering.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
