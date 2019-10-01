package com.abm.restinteg.models.config;

import com.abm.restinteg.models.HttpMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiDetails {

    @JsonProperty("name")
    private String name;

    @JsonProperty("path")
    private String path;

    @JsonProperty("method")
    private HttpMethod httpMethod;

    @JsonProperty("tests")
    private List<TestCase> testCases;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }
}
