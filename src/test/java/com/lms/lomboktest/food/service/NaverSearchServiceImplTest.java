package com.lms.lomboktest.food.service;

import com.lms.lomboktest.food.model.Food;
import com.lms.lomboktest.food.model.dto.SearchKeywordDto;
import com.lms.lomboktest.food.model.repository.FoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
class NaverSearchServiceImplTest {

    @InjectMocks
    private NaverSearchServiceImpl naverSearchServiceImpl;

    @Mock
    private FoodRepository foodRepository;

    @Test
    @DisplayName("없는 검색 키워드가 검색되는 케이스")
    void not_exist_keyword(){

        String keyword = "없는 키워드";
        //given
        given(foodRepository.findById(keyword)).willReturn(Optional.empty());
        given(foodRepository.save(any(Food.class))).willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SearchKeywordDto searchKeywordDto = naverSearchServiceImpl.saveFoodKeyword(keyword);

        //then
        Assertions.assertEquals(1 ,searchKeywordDto.getSearchCnt());

    }

    @Test
    @DisplayName("있는 검색 키워드가 검색되는 케이스")
    void exist_keyword(){

        String keyword = "있는 키워드";
        Long searchCnt = 10L;
        //given
        given(foodRepository.findById(keyword)).willReturn(Optional.of(new Food(keyword, searchCnt)));
        given(foodRepository.save(any(Food.class))).willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SearchKeywordDto searchKeywordDto = naverSearchServiceImpl.saveFoodKeyword(keyword);

        //then
        Assertions.assertEquals(11 ,searchKeywordDto.getSearchCnt());

    }

}