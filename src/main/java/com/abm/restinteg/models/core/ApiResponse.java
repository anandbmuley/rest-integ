package com.abm.restinteg.models.core;

import org.skyscreamer.jsonassert.JSONAssert;

public class ApiResponse {

    private String data;

    public ApiResponse() {
    }

    public ApiResponse(String data) {
        this.data = data;
    }

    public void validate(String responseBody) {
        JSONAssert.assertEquals(responseBody, data, true);
    }

    public String getData() {
        return data;
    }
}
