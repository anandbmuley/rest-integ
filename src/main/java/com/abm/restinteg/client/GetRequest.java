package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class GetRequest extends HttpRequest {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    public ResponseEntity<ApiResponse> call(ApiRequest apiRequest) throws RestClientException {
        try {
            Map<String, Object> uriVariables = Optional.ofNullable(apiRequest.getPathVariables()).orElseGet(Collections::emptyMap);
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, uriVariables);
            return ResponseEntity
                    .status(entity.getStatusCode())
                    .headers(entity.getHeaders())
                    .body(new ApiResponse(entity.getBody()));
        } catch (RestClientResponseException e) {
            return ResponseEntity
                    .status(e.getRawStatusCode())
                    .headers(e.getResponseHeaders())
                    .body(new ApiResponse(null));
        }
    }

}
