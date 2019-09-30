package com.abm.restinteg.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiDetails {

    @JsonProperty("name")
    private String name;

    @JsonProperty("path")
    private String path;

    @JsonProperty("method")
    private String method;

    @JsonProperty("tests")
    private List<TestCaseConfig> testCases;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public List<TestCaseConfig> getTestCases() {
        return testCases;
    }
}
