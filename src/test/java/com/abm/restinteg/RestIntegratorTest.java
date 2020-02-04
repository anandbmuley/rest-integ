package com.abm.restinteg;

import com.abm.restinteg.shared.StubFileLoader;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

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
        String encodedURl = UriUtils.encode("/books/Working with Emotional Intelligence", Charset.defaultCharset());
        wireMockServer.stubFor(
                get(urlEqualTo(BASE_PATH + encodedURl))
                        .willReturn(
                                aResponse().withStatus(204)
                                        .withBody(searchResponse)
                        )
        );

        new RestIntegrator();
    }


}