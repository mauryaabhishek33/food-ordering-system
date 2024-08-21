package com.demo.foodordering.food_ordering.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "order")
@Table(name = "order", schema = "public")
@Builder
public class Order {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cust_id" , insertable = false, updatable = false)
    private Customer customer;


    @Column(name = "cust_id")
    private Long customerId;

}
