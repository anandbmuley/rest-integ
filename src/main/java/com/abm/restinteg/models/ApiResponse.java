package com.abm.restinteg.models;

import com.abm.restinteg.models.config.ExpectedResponse;
import org.skyscreamer.jsonassert.JSONAssert;

public class ApiResponse {

    private String data;

    public ApiResponse(String data) {
        this.data = data;
    }

    public void validate(ExpectedResponse expectedResponse) throws Exception {
        JSONAssert.assertEquals(expectedResponse.getBody(), data, true);
    }

}
