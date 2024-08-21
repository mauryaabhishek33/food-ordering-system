package com.demo.foodordering.food_ordering.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "restaurant")
@Table(name = "restaurant", schema = "public")
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String name;

    @Column(name = "rating_id")
    @Setter
    private int ratingId;

    @Column(name = "max_capacity")
    @Setter
    private int maxCapacity;

    @Column(name = "curr_capacity")
    @Setter
    private int currentCapacity;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<MenuItem> menuItems;


}
