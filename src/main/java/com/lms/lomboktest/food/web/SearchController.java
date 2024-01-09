package com.lms.lomboktest.food.web;

import com.lms.lomboktest.config.PageHandler;
import com.lms.lomboktest.food.model.dto.FoodInputDto;
import com.lms.lomboktest.food.model.dto.SearchResponse;
import com.lms.lomboktest.food.service.KakaoSearchServiceImpl;
import com.lms.lomboktest.food.service.NaverSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    private final NaverSearchServiceImpl naverSearchServiceImpl;
    private final KakaoSearchServiceImpl kakaoSearchServiceImpl;

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
        SearchResponse searchResponse;
        if(ObjectUtils.isEmpty(food.getFood())) {
            searchResponse = new SearchResponse();
        }else{
            naverSearchServiceImpl.saveFoodKeyword(food.getFood());
            modelAndView.addObject("foodListWithCnt", naverSearchServiceImpl.foodListWithCount());
            searchResponse = naverSearchServiceImpl.foodSearch(food.getFood(), food.getSort() ,1);
        }

        if (searchResponse == null) {
            log.error("네이버 검색 API 호출 실패");
            PageHandler pageHandler = new PageHandler(Integer.parseInt(food.getPage()), 10);
            kakaoSearchServiceImpl.saveFoodKeyword(food.getFood());
            modelAndView.addObject("foodList", kakaoSearchServiceImpl.foodSearch(food.getFood(), "random", Integer.parseInt(food.getPage())).getDocumentList());
            modelAndView.addObject("pageHandler", pageHandler);
            modelAndView.addObject("keyword", food.getFood());
            modelAndView.addObject("foodListWithCnt", kakaoSearchServiceImpl.foodListWithCount());
            log.info("카카오 검색 API 호출 성공");
            return modelAndView;
        }
        log.info("검색 API 및 keyword cnt 호출 성공");
        modelAndView.addObject("foodList", searchResponse.getItems());


        return modelAndView;
    }

  /*  @GetMapping("/foodCnt")
    public List<FoodCntDto> foodListWithCount() {
        log.info("음식 카운트 조회");
        return naverSearchServiceImpl.foodListWithCount();
    }
*/
}
