package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class DeleteRequest extends HttpRequest {

    public DeleteRequest(String url) {
        super(url);
    }

    @Override
    public ResponseEntity<ApiResponse> call(ApiRequest apiRequest) throws RestClientException {
        Map<String, Object> uriVariables = Optional.ofNullable(apiRequest.getPathVariables()).orElseGet(Collections::emptyMap);
        restTemplate.delete(url, uriVariables);
        return ResponseEntity.ok().build();
    }
}
