package com.lms.lomboktest.food.service;

import com.lms.lomboktest.food.model.dto.SearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverSearchServiceTest {


    @Autowired
    private NaverSearchService naverSearchService;

    @Test
    void localSearch() {

        // given
        String keyword = "갈비집";
        String sort = "random";
        int page = 1;

        // when
        SearchResponse result = naverSearchService.localSearch(keyword,sort,page);
        System.out.println(result);



    }

}