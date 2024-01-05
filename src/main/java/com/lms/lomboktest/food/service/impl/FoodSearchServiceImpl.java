package com.lms.lomboktest.food.service.impl;

import com.lms.lomboktest.food.model.dto.SearchResponse;
import com.lms.lomboktest.food.model.repository.FoodCntDto;

import java.util.List;

public interface FoodSearchServiceImpl {

    public SearchResponse localSearch(String query, String sort, int page);
    public List<FoodCntDto> foodListWithCount();
    public void saveFoodKeyword(String query);
}
