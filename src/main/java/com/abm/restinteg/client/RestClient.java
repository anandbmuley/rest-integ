package com.abm.restinteg.client;

import com.abm.restinteg.models.core.ExpectedResponse;
import com.abm.restinteg.models.core.TestScenario;
import com.abm.restinteg.models.core.ApiResponse;
import com.abm.restinteg.models.config.ExpectedResponseConfig;
import com.abm.restinteg.validators.ResponseValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static com.abm.restinteg.client.HttpRequestFactory.get;

public class RestClient {

    protected RestTemplate restTemplate;
    private ResponseEntity<ApiResponse> apiResponseResponseEntity;
    private TestScenario testScenario;
    private ResponseValidator responseValidator;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        responseValidator = new ResponseValidator();
    }

    public RestClient call(TestScenario testScenario) {
        try {
            apiResponseResponseEntity = get(testScenario).call(testScenario);
        } catch (RestClientResponseException e) {
            apiResponseResponseEntity = ResponseEntity.status(e.getRawStatusCode()).body(new ApiResponse(e.getResponseBodyAsString()));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("UNABLE TO CONNECT TO SERVICE..." + e.getMessage());
            throw e;
        }
        this.testScenario = testScenario;
        return this;
    }

    public void validate(ExpectedResponse expectedResponse) throws Exception {
        responseValidator.validate(apiResponseResponseEntity, expectedResponse);
    }

    public ResponseEntity<ApiResponse> getApiResponseResponseEntity() {
        return apiResponseResponseEntity;
    }
}
