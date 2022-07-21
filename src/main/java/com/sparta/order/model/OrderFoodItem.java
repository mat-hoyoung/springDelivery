package com.sparta.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderFoodItem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;



    public OrderFoodItem(String name,int quantity, int price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }


}