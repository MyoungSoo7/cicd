package com.lms.lomboktest.food.service.kakao;


import com.lms.lomboktest.food.dto.SearchLocalRes;
import com.lms.lomboktest.food.entity.Food;
import com.lms.lomboktest.food.service.FoodRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategorySearchService {

    private static final String KAKAO_LOCAL_CATEGORY_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private static final String FOOD_CATEGORY = "FD6";
    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;
    private final FoodRepositoryService foodRepositoryService;

    @Retryable(
            exceptionExpression = "RuntimeException.class",
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000)
    )
    public SearchLocalRes requestFoodCategorySearch(String query, int page) {

        foodRepositoryService.save(Food.builder().food(query).build());

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_CATEGORY_SEARCH_URL);
        uriBuilder.queryParam("query", query);
        uriBuilder.queryParam("category_group_code", FOOD_CATEGORY);
        uriBuilder.queryParam("size", 10);
        uriBuilder.queryParam("page", page);
        URI uri = uriBuilder.build().encode().toUri();

        var headers  = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};
        var responseEntity = new RestTemplate()
                .exchange(
                        uri,
                        HttpMethod.GET,
                        httpEntity,
                        responseType
                );

        String responseBody = String.valueOf(responseEntity.getBody());
        SearchLocalRes searchLocalRes = responseEntity.getBody();

        return searchLocalRes;

    }

    @Recover
    public SearchLocalRes recover(RuntimeException e, String address) {
        log.error("All the retries failed. address: {}, error : {}", address, e.getMessage());
        return null;
    }
}
