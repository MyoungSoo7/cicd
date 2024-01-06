package com.lms.lomboktest;

public class IntegrationTest {

/*
    @Autowired
    private SearchService searchService;

    @Autowired
    private SearchKeywordRepository searchKeywordRepository;

    private final String existingKeyword = "존재하는 검색어";

    private final Long existingSearchCnt = 22L;

    @BeforeEach
    void setUp() {
        searchKeywordRepository.deleteAll();
        searchKeywordRepository.save(new SearchKeyword(existingKeyword, existingSearchCnt));
    }

    @Test
    @DisplayName("존재하는 키워드가 검색되는 케이스")
    void exist_keyword() {
        //when
        SearchKeywordDto res = searchService.save(existingKeyword);

        //then
        Assertions.assertEquals(existingSearchCnt + 1, res.getSearchCnt());
    }

    @Test
    @DisplayName("존재하는 키워드가 동시에 검색되는 케이스")
    void exist_keyword_concurrently() {
        AtomicReference<Throwable> e = new AtomicReference<>();

        //when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> searchService.save(existingKeyword)),
                CompletableFuture.runAsync(() -> searchService.save(existingKeyword))
        ).exceptionally(throwable -> {
            e.set(throwable.getCause());
            return null;
        }).join();

        //then
        Assertions.assertNotNull(e.get());
        Assertions.assertInstanceOf(ObjectOptimisticLockingFailureException.class, e.get());
        Assertions.assertEquals(existingSearchCnt + 1, searchKeywordRepository.findById(existingKeyword).get().getSearchCnt());
    }*/
}
