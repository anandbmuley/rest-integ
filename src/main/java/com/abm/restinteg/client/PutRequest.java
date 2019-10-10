package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;

public class PutRequest extends HttpRequest {

    public PutRequest(String url) {
        super(url);
    }

    @Override
    public ResponseEntity<ApiResponse> call(ApiRequest apiRequest) throws RestClientException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(apiRequest.getBody(), httpHeaders);
        ResponseEntity<ApiResponse> data = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, ApiResponse.class);
        return data;
    }
}
