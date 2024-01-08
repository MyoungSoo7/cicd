package com.lms.lomboktest.food.service.impl;

import com.lms.lomboktest.food.model.dto.SearchResponse;
import com.lms.lomboktest.food.model.repository.FoodCntDto;

import java.util.List;

public interface FoodSearchService {

    SearchResponse foodSearch(String query, String sort, int page);
    List<FoodCntDto> foodListWithCount();
    void saveFoodKeyword(String query);
}
