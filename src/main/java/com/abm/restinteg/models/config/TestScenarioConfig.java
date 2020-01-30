package com.abm.restinteg.models.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestScenarioConfig {

    @JsonProperty("name")
    private String name;

    @JsonProperty("request")
    private RequestConfig requestConfig;

    @JsonProperty("response")
    private ExpectedResponseConfig expectedResponseConfig;

    public String getName() {
        return name;
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    public ExpectedResponseConfig getExpectedResponseConfig() {
        return expectedResponseConfig;
    }
}
