package com.lms.lomboktest.food.service;


import com.lms.lomboktest.food.entity.Food;
import com.lms.lomboktest.food.repository.FoodCntDto;
import com.lms.lomboktest.food.repository.FoodReposiroty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodRepositoryService {

    private final FoodReposiroty foodReposiroty;

    //*** DB원자성 보장 => jpa사용 안하고 native query 사용 => update table set count = count +1 where food = food
    // 카프카 사용하는법도 있다.
    public Food save(Food food) {
        return foodReposiroty.save(food);
    }

    public List<FoodCntDto> findFoodCnt() {
        return  foodReposiroty.findFoodCnt();
    }


}
