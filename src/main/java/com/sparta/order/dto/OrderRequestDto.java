package com.sparta.order.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;

    private List<OrderFoodRequestDto> foods;
}