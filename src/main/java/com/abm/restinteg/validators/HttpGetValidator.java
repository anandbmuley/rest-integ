package com.abm.restinteg.validators;

import com.abm.restinteg.models.ApiResponse;
import com.abm.restinteg.models.config.ExpectedResponse;
import org.springframework.http.ResponseEntity;

public class HttpGetValidator extends ResponseValidator {

    @Override
    public boolean validate(ResponseEntity<ApiResponse> responseResponseEntity, ExpectedResponse expectedResponse) throws Exception {
        responseResponseEntity.getBody().validate(expectedResponse);
        if (expectedResponse.getStatusCode() != responseResponseEntity.getStatusCodeValue()) {
            throw new Exception("Status Code Mismatch : [ Expected : " + expectedResponse.getStatusCode() + ", Actual : " + responseResponseEntity.getStatusCodeValue() + " ]");
        }
        return false;
    }
}
