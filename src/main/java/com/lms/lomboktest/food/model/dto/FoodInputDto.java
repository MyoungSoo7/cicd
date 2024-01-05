package com.lms.lomboktest.food.model.dto;

import lombok.Data;

@Data
public class FoodInputDto {

    private String food;
    private String sort;
    private String page;
}
