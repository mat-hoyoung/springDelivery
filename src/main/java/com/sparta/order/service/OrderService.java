package com.sparta.order.service;

import com.sparta.order.dto.OrderFoodRequestDto;
import com.sparta.order.dto.OrderRequestDto;
import com.sparta.order.dto.OrderResponseDto;
import com.sparta.order.model.Food;
import com.sparta.order.model.Order;
import com.sparta.order.model.OrderFoodItem;
import com.sparta.order.model.Restaurant;
import com.sparta.order.repository.FoodRepository;
import com.sparta.order.repository.OrderFoodItemRepository;
import com.sparta.order.repository.OrderRepository;
import com.sparta.order.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    private final FoodRepository foodRepository;

    private final OrderFoodItemRepository orderFoodItemRepository;




    public OrderResponseDto requestOrder(OrderRequestDto orderRequestDto) {

        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 레스토랑입니다.")
        );
        int totalPrice = 0;
        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();


        List<OrderFoodRequestDto> OrderFoodList = orderRequestDto.getFoods();
        List<Integer> quantityList = new ArrayList<>();
        List<Food> foodList = new ArrayList<>();
        List<OrderFoodItem>  orderFoodItemList= new ArrayList<>();

        for(OrderFoodRequestDto  orderFood : OrderFoodList){
            int quantity = orderFood.getQuantity();
            if(quantity<1 || quantity>100){
                throw new IllegalArgumentException(" 값이 올바르지 않습니다.");
            }
            Food food = foodRepository.findByRestaurantAndId(restaurant,orderFood.getId());
            OrderFoodItem orderFoodItem = new OrderFoodItem(food.getName(),quantity ,food.getPrice()*quantity);
            orderFoodItemList.add(orderFoodItem);
            foodList.add(food);
            quantityList.add(quantity);
            totalPrice = totalPrice + quantity*food.getPrice();
            if(totalPrice<restaurant.getMinOrderPrice()){
                throw new IllegalArgumentException("최소 가격이 아닙니다");
            }
        }
        totalPrice = totalPrice+deliveryFee;

        Order order = new Order(orderFoodItemList,deliveryFee,totalPrice,restaurant);
        OrderResponseDto orderResponseDto = new OrderResponseDto(order);
        orderRepository.save(order);
        orderFoodItemRepository.saveAll(orderFoodItemList);



        return orderResponseDto;


    }

    public List<OrderResponseDto> getOrders() {
        List<Order>orderList = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for (Order order:orderList){
            OrderResponseDto orderResponseDto = new OrderResponseDto(order);
            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }
}