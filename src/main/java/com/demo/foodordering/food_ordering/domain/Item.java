package com.demo.foodordering.food_ordering.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "item")
@Table(name = "item", schema = "public")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    private String name;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<MenuItem> menu;


}
