package com.sparta.order.controller;

import com.sparta.order.dto.RestaurantDto;
import com.sparta.order.model.Restaurant;
import com.sparta.order.repository.RestaurantRepository;
import com.sparta.order.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;


    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantService.registerRestaurant(restaurantDto);
        return restaurant;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant() {
        List<Restaurant> restaurantList = restaurantService.getAllRestaurant();

        return restaurantList;
    }

}
