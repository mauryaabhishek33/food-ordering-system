package com.demo.foodordering.food_ordering.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "order_item")
@Table(name = "order_item", schema = "public")
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "res_id")
    @Setter
    private Long resutaurantId;

    @Column(name = "item_id")
    @Setter
    private Long itemId;

    @Column(name = "item_name")
    @Setter
    private String itemName;

    @Column(name = "order_id", insertable=false, updatable=false)
    @Setter
    private Long orderId;

    @Column(name = "qty")
    private int quantity;

}
