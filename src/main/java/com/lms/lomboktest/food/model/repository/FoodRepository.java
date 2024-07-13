package com.lms.lomboktest.food.model.repository;


import com.lms.lomboktest.food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, String> {



}
