package com.abm.restinteg.client;

import com.abm.restinteg.models.core.TestScenario;
import com.abm.restinteg.models.core.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class DeleteRequest extends HttpRequest {

    public DeleteRequest(String url, RestTemplate restTemplate) {
        super(url, restTemplate);
    }

    @Override
    public ResponseEntity<ApiResponse> call(TestScenario testScenario) throws RestClientException {
        Map<String, Object> uriVariables = Optional.ofNullable(testScenario.getPathVariables()).orElseGet(Collections::emptyMap);
        restTemplate.delete(url, uriVariables);
        return ResponseEntity.ok().build();
    }
}
