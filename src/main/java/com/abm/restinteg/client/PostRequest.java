package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class PostRequest extends HttpRequest {

    public PostRequest(String url) {
        super(url);
    }

    @Override
    public ResponseEntity<ApiResponse> call(ApiRequest apiRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(apiRequest.getBody(), httpHeaders);
        ResponseEntity<ApiResponse> data = restTemplate.postForEntity(url, httpEntity, ApiResponse.class);
        return data;
    }
}
