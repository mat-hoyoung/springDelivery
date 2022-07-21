package com.sparta.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFoodResponseDto {
    private String name;
    private int quantity;
    private int price;
}