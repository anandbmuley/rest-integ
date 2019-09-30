package com.abm.restinteg.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class ApiResponse {

    private List<Map<String, Object>> data;
    private ObjectMapper mapper;

    private ApiResponse() {
        mapper = new ObjectMapper();
    }

    public ApiResponse(List<Map<String, Object>> data) {
        this();
        this.data = data;
    }

    public int size() {
        return data.size();
    }

    public void validate(String expectedResponse) throws Exception {
        List<Map<String, Object>> expectedResponseData = mapper.readValue(expectedResponse, List.class);
        if (expectedResponseData.size() != size()) {
            throw new Exception("Assertion error");
        }
    }


}
