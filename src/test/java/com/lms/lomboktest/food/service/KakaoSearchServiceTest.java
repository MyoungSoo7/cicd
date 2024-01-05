package com.lms.lomboktest.food.service;

import com.lms.lomboktest.food.model.repository.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class KakaoSearchServiceTest {


    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private KakaoSearchService kakaoSearchService;

    @Test
    void localSearch() {



    }


}