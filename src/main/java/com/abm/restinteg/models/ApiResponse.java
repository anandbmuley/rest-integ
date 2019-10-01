package com.abm.restinteg.models;

import com.abm.restinteg.models.config.ExpectedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ApiResponse {

    protected ObjectMapper mapper;

    protected ApiResponse() {
        mapper = new ObjectMapper();
    }

    public abstract int size();

    public abstract void validate(ExpectedResponse expectedResponse) throws Exception;

}
