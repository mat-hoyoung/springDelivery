package com.sparta.order.model;


import com.sparta.order.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Restaurant {


    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant(RestaurantDto restaurantDto) {
        this.name = restaurantDto.getName();
        this.deliveryFee = restaurantDto.getDeliveryFee();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
    }

}
