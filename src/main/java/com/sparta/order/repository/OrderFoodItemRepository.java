package com.sparta.order.repository;

import com.sparta.order.model.OrderFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodItemRepository extends JpaRepository<OrderFoodItem,Long> {

}