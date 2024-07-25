package com.lms.lomboktest.food.service;

import com.lms.lomboktest.food.model.Food;
import com.lms.lomboktest.food.model.dto.SearchKeywordDto;
import com.lms.lomboktest.food.model.dto.SearchResponse;
import com.lms.lomboktest.food.model.repository.FoodRepository;
import com.lms.lomboktest.food.service.impl.FoodSearchService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverSearchServiceImpl implements FoodSearchService  {

    private final FoodRepository foodRepository;
    @Value(value = "${naver.client.id}")
    private String naverClientId;
    @Value(value = "${naver.client.secret}")
    private String naverClientSecret;
    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;
    private final KakaoSearchServiceImpl kakaoSearchServiceImpl;

    @CircuitBreaker(name = "circuit-sample-common", fallbackMethod = "searchFoodFallback")
    @Override
    public SearchResponse foodSearch(String query, String sort , int page) {

        UriComponentsBuilder uriBuilder = getUriComponentsBuilder(query, sort);
        var responseEntity = getSearchResponseResponseEntity(uriBuilder);
        SearchResponse sr = responseEntity.getBody();
        return sr;
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

    @Override
    public List<Food> foodListWithCount(){
        /*List<Food> foodList = redisTemplateService.findAll();
        if(!CollectionUtils.isEmpty(foodList)) return foodList;*/
        return foodRepository.findAll();
    }

    @Transactional
    @Override
    public SearchKeywordDto saveFoodKeyword(String query) {
        Food food = foodRepository.findById(query).orElse(new Food(query, 0L));
        food.increaseSearchCnt();
        log.info("음식 키워드 저장");
        return new SearchKeywordDto(foodRepository.save(food));
    }

    private SearchResponse searchFoodFallback(Throwable t) {
        SearchResponse sr = null;
        return sr;
    }


}
