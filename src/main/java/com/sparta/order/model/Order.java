package com.sparta.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderFoodItem> orderFoodItemList;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID" , nullable = false)
    private Restaurant restaurant;

    public Order(List<OrderFoodItem> orderFoodItemList, int deliveryFee, int totalPrice,Restaurant restaurant){
        this.orderFoodItemList = orderFoodItemList;
        this.deliveryFee =deliveryFee;
        this.totalPrice = totalPrice;
        this.restaurant = restaurant;
    }


}