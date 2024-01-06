package com.lms.lomboktest.food.web;

import com.lms.lomboktest.config.PageHandler;
import com.lms.lomboktest.food.model.dto.FoodInputDto;
import com.lms.lomboktest.food.model.dto.SearchResponse;
import com.lms.lomboktest.food.model.repository.FoodCntDto;
import com.lms.lomboktest.food.service.KakaoSearchService;
import com.lms.lomboktest.food.service.NaverSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final NaverSearchService naverSearchService;
    private final KakaoSearchService kakaoSearchService;

    @RequestMapping("/")
    public String index() {
        return "main";
    }

    //** redis 낙관적 락 , 비관적락 lock 사용
    //*** redis db 커넥션 과부하 해결
    @PostMapping("/search")
    public ModelAndView searchFoodApi(@ModelAttribute FoodInputDto food)   {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("food-search");
        SearchResponse searchResponse = new SearchResponse();
        if(ObjectUtils.isEmpty(food.getFood())) {
            searchResponse = new SearchResponse();
            log.info("검색어가 없습니다.");
        }else{
            //new Thread(naverSearchService.saveFoodKeyword(food.getFood())).start();
            naverSearchService.saveFoodKeyword(food.getFood());
            searchResponse = naverSearchService.localSearch(food.getFood(), food.getSort() ,1);
        }

        if (searchResponse == null) {
            log.error("네이버 검색 API 호출 실패");
            PageHandler pageHandler = new PageHandler(Integer.parseInt(food.getPage()), 10);
            kakaoSearchService.saveFoodKeyword(food.getFood());
            log.info("음식 키워드 저장 완료");
            modelAndView.addObject("foodList", kakaoSearchService.localSearch(food.getFood(), "random", Integer.parseInt(food.getPage())).getDocumentList());
            modelAndView.addObject("pageHandler", pageHandler);
            modelAndView.addObject("keyword", food.getFood());
            modelAndView.addObject("foodListWithCnt", kakaoSearchService.foodListWithCount());
            log.info("카카오 검색 API 호출 성공");
            return modelAndView;
        }
        log.info("검색 API 및 keyword cnt 호출 성공");
        modelAndView.addObject("foodList", searchResponse.getItems());
        modelAndView.addObject("foodListWithCnt", naverSearchService.foodListWithCount());

        return modelAndView;
    }

    @GetMapping("/foodCnt")
    public List<FoodCntDto> foodListWithCount() {
        log.info("음식 카운트 조회");
        return naverSearchService.foodListWithCount();
    }

}
