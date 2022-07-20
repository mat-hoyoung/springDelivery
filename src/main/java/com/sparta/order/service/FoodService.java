package com.sparta.order.service;

import com.sparta.order.dto.FoodDto;
import com.sparta.order.model.Food;
import com.sparta.order.model.Restaurant;
import com.sparta.order.repository.FoodRepository;
import com.sparta.order.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FoodService {

    private final FoodRepository foodRepository;

    private final RestaurantRepository restaurantRepository;


    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;

    }

    public void registerFood(List<FoodDto> foodDtoList, Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                 .orElseThrow(() -> new NullPointerException("없는 레스토랑 입니다."));
        List<Food> existFoodList = foodRepository.findAllByRestaurant(restaurant);
        List<String> existFoodNameList = new ArrayList<>();
        List<String> typingFoodList = new ArrayList<>();

        for(Food existFood : existFoodList) {
            existFoodNameList.add(existFood.getName());
            }
        //foodDtoList 에서 FoodDto 객체를 하나씩 꺼내옴.
        for(FoodDto foodDto : foodDtoList) {

            String name = foodDto.getName();
            int price = foodDto.getPrice();


            if(typingFoodList.contains(name)){
                throw new IllegalArgumentException("명칭이 똑같습니다.");
            }

            if(existFoodNameList.contains(name)) {
                throw new IllegalArgumentException("음식 이름이 똑같습니다.");
            }

            typingFoodList.add(name);

            if (price < 100 || price > 1000000) {
                throw new IllegalArgumentException("음식 가격은 100원 이상 ~ 1000000원 이하로 설정해주십시오.");
            }
            if (price % 100 != 0) {
                throw new IllegalArgumentException("가격설정은 100원 위 이상으로 입력 가능합니다.");
            }
            Food food = new Food(foodDto, restaurant);
            foodRepository.save(food);

        }

    }

    public List<Food> getAllfood(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NullPointerException("없는 레스토랑 입니다."));

        List<Food> foodList = foodRepository.findAllByRestaurant(restaurant);

        return foodList;
    }
}
