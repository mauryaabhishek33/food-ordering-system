package com.demo.foodordering.food_ordering.repository;

import com.demo.foodordering.food_ordering.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Nullable
    Optional<MenuItem> findFirstByRestaurantIdEqualsAndItemIdEquals(@NonNull Long restaurantId, @NonNull Long itemId);

    List<MenuItem> findByItemIdEquals(Long itemId);
}