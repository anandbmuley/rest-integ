package com.abm.restinteg.models.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RestIntegration {

    @JsonProperty("ServiceName")
    private String serviceName;

    @JsonProperty("Version")
    private String version;

    @JsonProperty("BasePath")
    private String basePath;

    @JsonProperty("APIS")
    private List<ApiDetails> apiDetails;

    public String getServiceName() {
        return serviceName;
    }

    public String getVersion() {
        return version;
    }

    public String getBasePath() {
        return basePath;
    }

    public List<ApiDetails> getApiDetails() {
        return apiDetails;
    }
}
