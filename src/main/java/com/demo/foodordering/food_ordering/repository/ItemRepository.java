package com.demo.foodordering.food_ordering.repository;

import com.demo.foodordering.food_ordering.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Optional<Item> findByNameEqualsIgnoreCase(String name);
}
