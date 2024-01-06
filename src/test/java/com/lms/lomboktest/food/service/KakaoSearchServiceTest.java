package com.lms.lomboktest.food.service;

import com.lms.lomboktest.food.model.dto.SearchResponse;
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

        // given
        String keyword = "갈비집";
        String sort = "random";
        int page = 1;

        // when
    /*    SearchResponse result = kakaoSearchService.localSearch(keyword,sort,page);
        System.out.println(result);*/

    }


}