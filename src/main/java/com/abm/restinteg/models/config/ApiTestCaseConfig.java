package com.abm.restinteg.models.config;

import com.abm.restinteg.models.core.HttpMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiTestCaseConfig {

    @JsonProperty("name")
    private String name;

    @JsonProperty("path")
    private String path;

    @JsonProperty("method")
    private HttpMethod httpMethod;

    @JsonProperty("tests")
    private List<TestScenarioConfig> testScenarioConfigs;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public List<TestScenarioConfig> getTestScenarioConfigs() {
        return testScenarioConfigs;
    }
}
