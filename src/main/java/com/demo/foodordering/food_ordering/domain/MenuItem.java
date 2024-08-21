package com.demo.foodordering.food_ordering.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "menu_item")
@Table(name = "menu_item", schema = "public")
@Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "res_id", insertable=false, updatable=false)
    @Setter
    private Long restaurantId;

    @Column(name = "item_id", insertable=false, updatable=false)
    private Long itemId;

    @Setter
    private double price;


    @ManyToOne
    @JoinColumn(name = "res_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Item item;


}
