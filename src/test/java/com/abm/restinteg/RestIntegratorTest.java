package com.abm.restinteg;

import com.abm.restinteg.shared.StubFileLoader;
import com.abm.restinteg.shared.ScenarionStubbing;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class RestIntegratorTest {

    private static final String BASE_PATH = "/testing-rest/api";
    private WireMockServer wireMockServer;
    private StubFileLoader stubFileLoader;

    @BeforeEach
    void setUp() {
        configureFor("localhost", 8090);
        wireMockServer = new WireMockServer(8090);
        stubFileLoader = new StubFileLoader();
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void shouldTestHappyPath() throws Exception {
        String requestBody = stubFileLoader.getRequestFile("add-book-request.json");
        wireMockServer.stubFor(
                post(urlEqualTo(BASE_PATH + "/books"))
                        .withRequestBody(equalToJson(requestBody))
                        .willReturn(
                                aResponse().withStatus(200)
                        )
        );

        String secondStubBody = stubFileLoader.getRequestFile("add-book-missing-name-request.json");
        String responseBody = stubFileLoader.getResponseFile("add-book-missing-name-response.json");

        wireMockServer.stubFor(
                post(urlEqualTo(BASE_PATH + "/books"))
                        .withRequestBody(equalToJson(secondStubBody))
                        .willReturn(
                                aResponse().withStatus(400)
                                        .withBody(responseBody)
                        )
        );

        String putRequestBody = stubFileLoader.getRequestFile("update-book-request.json");

        wireMockServer.stubFor(
                put(urlEqualTo(BASE_PATH + "/books"))
                        .withRequestBody(equalToJson(putRequestBody))
                        .willReturn(
                                aResponse().withStatus(204)
                        )
        );

        String searchResponse = stubFileLoader.getResponseFile("search-books-response.json");
        wireMockServer.stubFor(
                get(urlEqualTo(BASE_PATH + "/books/Working%20with%20Emotional%20Intelligence"))
                        .inScenario(ScenarionStubbing.SUCCESSFUL_BOOK_SEARCH)
                        .whenScenarioStateIs(Scenario.STARTED)
                        .willReturn(
                                aResponse().withStatus(200)
                                        .withBody(searchResponse)
                        )
                        .willSetStateTo(ScenarionStubbing.STATE_COMPLETED)
        );

        String notFoundErrorResponse = stubFileLoader.getResponseFile("search-books-not-found-error-response.json");
        wireMockServer.stubFor(
                get(urlEqualTo(BASE_PATH + "/books/Mein"))
                        .inScenario(ScenarionStubbing.SUCCESSFUL_BOOK_SEARCH)
                        .whenScenarioStateIs(ScenarionStubbing.STATE_COMPLETED)
                        .willReturn(
                                aResponse().withStatus(404)
                                        .withBody(notFoundErrorResponse)
                        )
        );


        wireMockServer.stubFor(
                delete(urlEqualTo(BASE_PATH + "/books/1234"))
                        .inScenario(ScenarionStubbing.SUCCESSFUL_BOOK_DELETE)
                        .whenScenarioStateIs(Scenario.STARTED)
                        .willReturn(
                                aResponse().withStatus(200)
                        )
                        .willSetStateTo(ScenarionStubbing.STATE_COMPLETED)
        );

        wireMockServer.stubFor(
                delete(urlEqualTo(BASE_PATH + "/books/"))
                        .inScenario(ScenarionStubbing.SUCCESSFUL_BOOK_DELETE)
                        .whenScenarioStateIs(ScenarionStubbing.STATE_COMPLETED)
                        .willReturn(
                                aResponse().withStatus(200)
                        )
        );

        new RestIntegrator();
    }


}