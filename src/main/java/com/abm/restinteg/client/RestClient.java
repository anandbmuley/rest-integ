package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import com.abm.restinteg.models.config.ExpectedResponse;
import com.abm.restinteg.validators.ResponseValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.abm.restinteg.client.HttpRequestFactory.get;

public class RestClient {

    protected RestTemplate restTemplate;
    private ResponseEntity<ApiResponse> apiResponseResponseEntity;
    private ApiRequest apiRequest;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestClient call(ApiRequest apiRequest) {
        apiResponseResponseEntity = get(apiRequest).call(apiRequest);
        this.apiRequest = apiRequest;
        return this;
    }

    public void validate(ExpectedResponse expectedResponse) throws Exception {
        ResponseValidatorFactory
                .get(apiRequest.getHttpMethod())
                .validate(apiResponseResponseEntity,expectedResponse);

    }

}
