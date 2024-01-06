package com.lms.lomboktest.config;

/*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class RouteConfigurationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void addHeaderRouteTest() throws Exception {
        //Stubs
        stubFor(get(urlEqualTo("/search"))
                .willReturn(aResponse()
                        .withBody("{\"headers\":{\"Role\":\"hello-api\"}}")
                        .withHeader("Content-Type", "application/json")));

        webClient
            .get().uri("/get")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.headers.Role").isEqualTo("hello-api");
    }



}*/
