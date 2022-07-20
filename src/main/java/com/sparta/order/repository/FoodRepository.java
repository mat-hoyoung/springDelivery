package com.sparta.order.repository;

import com.sparta.order.model.Food;
import com.sparta.order.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurant(Restaurant restaurant);

}
