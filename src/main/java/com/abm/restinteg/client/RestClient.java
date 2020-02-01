package com.abm.restinteg.client;

import com.abm.restinteg.models.core.ApiResponse;
import com.abm.restinteg.models.core.ExpectedResponse;
import com.abm.restinteg.models.core.TestScenario;
import com.abm.restinteg.validators.ResponseValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.abm.restinteg.client.HttpRequestFactory.get;

public class RestClient {

    private final Logger LOGGER = Logger.getLogger(RestClient.class.getSimpleName());

    protected RestTemplate restTemplate;
    private ResponseEntity<ApiResponse> apiResponseResponseEntity;
    private ResponseValidator responseValidator;

    public RestClient(RestTemplate restTemplate, ResponseValidator responseValidator) {
        this.restTemplate = restTemplate;
        this.responseValidator = responseValidator;
    }

    public RestClient call(TestScenario testScenario) {
        try {
            apiResponseResponseEntity = get(testScenario, restTemplate).call(testScenario);
        } catch (RestClientResponseException e) {
            apiResponseResponseEntity = ResponseEntity.status(e.getRawStatusCode()).body(new ApiResponse(e.getResponseBodyAsString()));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "UNABLE TO CONNECT TO SERVICE..." + e.getMessage());
            throw e;
        }
        return this;
    }

    public void validate(ExpectedResponse expectedResponse) throws Exception {
        responseValidator.validate(apiResponseResponseEntity, expectedResponse);
    }

}
