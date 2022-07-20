package com.sparta.order.model;

import com.sparta.order.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity

public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false)
    private int price;


    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)

    private Restaurant restaurant;
    public Food(FoodDto foodDto, Restaurant restaurant) {
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurant = restaurant;
    }

}
