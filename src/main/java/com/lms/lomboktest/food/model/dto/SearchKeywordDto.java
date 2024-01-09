package com.lms.lomboktest.food.model.dto;

import com.lms.lomboktest.food.model.Food;
import lombok.Data;

@Data
public class SearchKeywordDto {
    private final String food;
    private final Long searchCnt;

    public SearchKeywordDto(Food food) {
        this.food = food.getFood();
        this.searchCnt = food.getSearchCnt();
    }
}
