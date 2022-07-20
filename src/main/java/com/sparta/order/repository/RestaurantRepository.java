package com.sparta.order.repository;

import com.sparta.order.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


}
