package com.abm.restinteg.client;

import com.abm.restinteg.models.core.TestScenario;
import com.abm.restinteg.models.core.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public abstract class HttpRequest {

    protected RestTemplate restTemplate;
    protected final String url;

    public HttpRequest(String url) {
        this.restTemplate = new RestTemplate();
        this.url = url;
    }

    public abstract ResponseEntity<ApiResponse> call(TestScenario testScenario) throws RestClientException;

}
