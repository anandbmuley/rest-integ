package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import com.abm.restinteg.models.config.ExpectedResponse;
import com.abm.restinteg.validators.ResponseValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.abm.restinteg.client.HttpRequestFactory.get;

public class RestClient {

    protected RestTemplate restTemplate;
    private ResponseEntity<ApiResponse> apiResponseResponseEntity;
    private ApiRequest apiRequest;
    private ResponseValidator responseValidator;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        responseValidator = new ResponseValidator();
    }

    public RestClient call(ApiRequest apiRequest) {
        try {
            apiResponseResponseEntity = get(apiRequest).call(apiRequest);
        } catch (RestClientException e) {
            System.err.println("UNABLE TO CONNECT TO SERVICE...");
            throw e;
        }
        this.apiRequest = apiRequest;
        return this;
    }

    public void validate(ExpectedResponse expectedResponse) throws Exception {
        responseValidator.validate(apiResponseResponseEntity, expectedResponse);

    }

}
