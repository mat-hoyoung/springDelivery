package com.sparta.order.controller;

import com.sparta.order.dto.FoodDto;
import com.sparta.order.model.Food;
import com.sparta.order.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {

        this.foodService = foodService;
    }

    @PostMapping("restaurant/{restaurantId}/food/register")
    public void registerFood(
            @RequestBody List<FoodDto> foodDtoList
            , @PathVariable Long restaurantId) {
        foodService.registerFood(foodDtoList, restaurantId);
    }

    @GetMapping("restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable Long restaurantId) {

        List<Food> foodList = foodService.getAllfood(restaurantId);

        return foodList;
    }
}


