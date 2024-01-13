/*
package com.lms.lomboktest.food.service;

import com.lms.lomboktest.food.model.Food;
import com.lms.lomboktest.food.model.dto.SearchKeywordDto;
import com.lms.lomboktest.food.model.repository.FoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
class NaverSearchServiceImplTest2 {

    @Autowired
    private NaverSearchServiceImpl naverSearchServiceImpl;

    @Autowired
    private FoodRepository foodRepository;

    private final String exist_keyword = "api";
    private final Long exist_searchCnt = 10L;
    @BeforeEach
    void setUp(){
        foodRepository.deleteAll();
        foodRepository.save(new Food(exist_keyword, exist_searchCnt));
    }

    @Test
    @DisplayName("존재하는 검색어가 검색되는 케이스")
    void exist_keyword(){
        //when
        SearchKeywordDto searchKeywordDto = naverSearchServiceImpl.saveFoodKeyword(exist_keyword);

        //then
        Assertions.assertEquals(exist_searchCnt+1, searchKeywordDto.getSearchCnt());
    }


    @Test
    @DisplayName("존재하는 키워드가 동시에 검색되는 케이스")
    void exist_keyword_concurrently(){
        AtomicReference<Throwable> e = new AtomicReference<>();

        //when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        naverSearchServiceImpl.saveFoodKeyword(exist_keyword);
                    } catch (Throwable ex) {
                        e.set(ex);
                    }
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        naverSearchServiceImpl.saveFoodKeyword(exist_keyword);
                    } catch (Throwable ex) {
                        e.set(ex);
                    }
                })
        ).exceptionally(throwable -> {
            e.set(throwable.getCause());
            return null;
        }).join();


        //then
        Assertions.assertNotNull(e.get());
        Assertions.assertInstanceOf(ObjectOptimisticLockingFailureException.class, e.get());
        Assertions.assertEquals(exist_searchCnt+1, foodRepository.findById(exist_keyword).get().getSearchCnt());

    }


}
*/
