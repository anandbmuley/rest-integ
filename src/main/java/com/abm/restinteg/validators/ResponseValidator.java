package com.abm.restinteg.validators;

import com.abm.restinteg.models.ApiResponse;
import com.abm.restinteg.models.config.ExpectedResponse;
import org.springframework.http.ResponseEntity;

public abstract class ResponseValidator {

    public abstract boolean validate(ResponseEntity<ApiResponse> responseResponseEntity, ExpectedResponse expectedResponse) throws Exception;

}
