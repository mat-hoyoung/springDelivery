package com.sparta.order.service;


import com.sparta.order.dto.RestaurantDto;
import com.sparta.order.model.Restaurant;
import com.sparta.order.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant registerRestaurant(RestaurantDto restaurantDto) {
        if (restaurantDto.getMinOrderPrice() < 1000 || restaurantDto.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("최소 주문 가격에 어긋납니다.");

        }
        if (restaurantDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("최소 주문 가격은 100원 단위 이상으로 입력 가능합니다.");
        }

        if(restaurantDto.getDeliveryFee() < 0 || restaurantDto.getDeliveryFee() > 10000) {
            throw new IllegalArgumentException("기본 배달비는 0원~10000원까지 가능합니다. ");
        }
        if(restaurantDto.getDeliveryFee() % 500 !=0) {
            throw new IllegalArgumentException("기본 배달비는 500원 단위 이상으로 입력 가능합니다.  ");
        }
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getAllRestaurant() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        return restaurantList;

    }
}


