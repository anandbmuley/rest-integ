package com.abm.restinteg.models.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestCase {

    @JsonProperty("name")
    private String name;

    @JsonProperty("request")
    private SampleRequest sampleRequest;

    @JsonProperty("response")
    private ExpectedResponse expectedResponse;

    public String getName() {
        return name;
    }

    public SampleRequest getSampleRequest() {
        return sampleRequest;
    }

    public ExpectedResponse getExpectedResponse() {
        return expectedResponse;
    }
}
