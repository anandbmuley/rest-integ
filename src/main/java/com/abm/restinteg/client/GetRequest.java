package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

public class GetRequest extends HttpRequest {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    public ResponseEntity<ApiResponse> call(ApiRequest apiRequest) throws RestClientException {
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        return ResponseEntity
                .status(entity.getStatusCode())
                .headers(entity.getHeaders())
                .body(new ApiResponse(entity.getBody()));
    }

}
