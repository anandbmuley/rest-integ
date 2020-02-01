package com.abm.restinteg.client;

import com.abm.restinteg.models.core.TestScenario;
import com.abm.restinteg.models.core.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class PostRequest extends HttpRequest {

    public PostRequest(String url, RestTemplate restTemplate) {
        super(url, restTemplate);
    }

    @Override
    public ResponseEntity<ApiResponse> call(TestScenario testScenario) throws RestClientException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(testScenario.getRequestBody(), httpHeaders);
        ResponseEntity<ApiResponse> data = restTemplate.postForEntity(url, httpEntity, ApiResponse.class);
        return data;
    }
}
