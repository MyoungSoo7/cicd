package com.lms.lomboktest.food.service.impl;

import com.lms.lomboktest.food.model.Food;
import com.lms.lomboktest.food.model.dto.SearchKeywordDto;
import com.lms.lomboktest.food.model.dto.SearchResponse;

import java.util.List;

public interface FoodSearchService {

    SearchResponse foodSearch(String query, String sort, int page);
    List<Food> foodListWithCount();
    SearchKeywordDto saveFoodKeyword(String query);
}
