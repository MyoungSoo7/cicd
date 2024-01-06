package com.lms.lomboktest.food.service;


import com.lms.lomboktest.food.model.dto.SearchResponse;
import com.lms.lomboktest.food.model.Food;
import com.lms.lomboktest.food.model.repository.FoodCntDto;
import com.lms.lomboktest.food.model.repository.FoodRepository;
import com.lms.lomboktest.food.service.impl.FoodSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoSearchService implements FoodSearchServiceImpl {

    private static final String KAKAO_LOCAL_CATEGORY_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private static final String FOOD_CATEGORY = "FD6";
    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;
    private final FoodRepository foodRepository;


    @Retryable(
            exceptionExpression = "RuntimeException.class",
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000)
    )
    @Override
    public SearchResponse localSearch(String query, String sort, int page) {

        if(ObjectUtils.isEmpty(query)) {
            SearchResponse searchResponse = new SearchResponse();
            return searchResponse;
        }

        // URI 생성
        UriComponentsBuilder uriBuilder = getUriComponentsBuilder(query, page);
        // ResponseEntity 생성
        var responseEntity = getSearchResponseResponseEntity(uriBuilder.build().encode().toUri());

        String responseBody = String.valueOf(responseEntity.getBody());
        SearchResponse searchResponse = responseEntity.getBody();
        log.info("카카오 검색 API 호출 성공");
        return searchResponse;

    }

    @NotNull
    private UriComponentsBuilder getUriComponentsBuilder(String query, int page) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_CATEGORY_SEARCH_URL);
        uriBuilder.queryParam("query", query);
        uriBuilder.queryParam("category_group_code", FOOD_CATEGORY);
        uriBuilder.queryParam("size", 10);
        uriBuilder.queryParam("page", page);
        return uriBuilder;
    }

    @NotNull
    private ResponseEntity<SearchResponse> getSearchResponseResponseEntity(URI uri) {
        var headers  = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchResponse>(){};
        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );
        return responseEntity;
    }


    @Override
    public List<FoodCntDto> foodListWithCount() {
        return foodRepository.findFoodCnt();
    }

    @Override
    public void saveFoodKeyword(String query) {
        foodRepository.save(Food.builder().food(query).build());
    }

    @Recover
    public SearchResponse recover(RuntimeException e, String query) {
        log.error("All the retries failed. address: {}, error : {}", query, e.getMessage());
        return null;
    }
}
