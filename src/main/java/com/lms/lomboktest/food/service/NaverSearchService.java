package com.lms.lomboktest.food.service;


import com.lms.lomboktest.food.model.dto.SearchResponse;
import com.lms.lomboktest.food.model.Food;
import com.lms.lomboktest.food.model.repository.FoodCntDto;
import com.lms.lomboktest.food.model.repository.FoodRepository;
import com.lms.lomboktest.food.service.impl.FoodSearchServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverSearchService implements FoodSearchServiceImpl {

    private final FoodRepository foodRepository;
    @Value(value = "${naver.client.id}")
    private String naverClientId;
    @Value(value = "${naver.client.secret}")
    private String naverClientSecret;
    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @CircuitBreaker(name = "circuit-sample-3000", fallbackMethod = "searchFoodFallback")
    @Override
    public SearchResponse localSearch(String query, String sort , int page) {
        if(ObjectUtils.isEmpty(query)) {
            SearchResponse searchResponse = new SearchResponse();
            return searchResponse;
        }

        long start = System.currentTimeMillis();
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        log.info("[slowCall] call => {}ms", end - start);
        // URI 생성
        UriComponentsBuilder uriBuilder = getUriComponentsBuilder(query, sort);
        // ResponseEntity 생성
        var responseEntity = getSearchResponseResponseEntity(uriBuilder);
        //String responseBody = String.valueOf(responseEntity.getBody());
        SearchResponse sr = responseEntity.getBody();
        log.info("네이버 검색 API 호출 성공");
        return sr;
    }

    @NotNull
    private ResponseEntity<SearchResponse> getSearchResponseResponseEntity(UriComponentsBuilder uriBuilder) {
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchResponse>() {
        };

        var responseEntity = new RestTemplate()
                .exchange(
                        uriBuilder.build().encode().toUri(),
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );
        return responseEntity;
    }

    @NotNull
    private UriComponentsBuilder getUriComponentsBuilder(String query, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(naverLocalSearchUrl);
        uriBuilder.queryParam("query", query);
        uriBuilder.queryParam("display", "5");
        uriBuilder.queryParam("start", "1");
        uriBuilder.queryParam("sort", sort);
        return uriBuilder;
    }

    @Override
    public List<FoodCntDto> foodListWithCount(){
        return foodRepository.findFoodCnt();
    }


    //폴백 메스드 선언 방법

    @Override
    public void saveFoodKeyword(String query) {
        foodRepository.save(Food.builder().food(query).build());
        log.info("음식 키워드 저장");
    }

    private SearchResponse searchFoodFallback(Throwable t) {
        SearchResponse sr = null;
        return sr;
    }


}
