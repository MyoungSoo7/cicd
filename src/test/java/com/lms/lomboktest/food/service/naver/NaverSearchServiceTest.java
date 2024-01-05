package com.lms.lomboktest.food.service.naver;

import com.lms.lomboktest.food.service.NaverSearchService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class NaverSearchServiceTest {

    @InjectMocks
    private NaverSearchService naverSearchService;

   /* @InjectMocks
    private SearchService searchService;

    @Mock
    private SearchKeywordRepository searchKeywordRepository;

    @Test
    @DisplayName("없는 검색 키워드가 검색되는 케이스")
    void not_exist_keyword() {
        String keyword = "없는 검색어";

        //given
        given(searchKeywordRepository.findById(keyword)).willReturn(Optional.empty());
        given(searchKeywordRepository.save(any(SearchKeyword.class))).willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SearchKeywordDto res = searchService.save(keyword);

        //then
        Assertions.assertEquals(1, res.getSearchCnt());

    }

    @Test
    @DisplayName("없는 검색 키워드가 검색되는 케이스")
    void exist_keyword() {
        String keyword = "존재하는 검색어";
        Long searchCnt = 22L;

        //given
        given(searchKeywordRepository.findById(keyword)).willReturn(Optional.of(new SearchKeyword(keyword, searchCnt)));
        given(searchKeywordRepository.save(any(SearchKeyword.class))).willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SearchKeywordDto res = searchService.save(keyword);

        //then
        Assertions.assertEquals(23, res.getSearchCnt());
    }
*/



}