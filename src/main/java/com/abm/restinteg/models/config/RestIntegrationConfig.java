package com.abm.restinteg.models.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RestIntegrationConfig {

    @JsonProperty("ServiceName")
    private String serviceName;

    @JsonProperty("Version")
    private String version;

    @JsonProperty("BasePath")
    private String basePath;

    @JsonProperty("ApiVersion")
    private String apiVersion;

    @JsonProperty("APIS")
    private List<ApiTestCaseConfig> apiTestCaseConfigs;

    public String getServiceName() {
        return serviceName;
    }

    public String getVersion() {
        return version;
    }

    public String getBasePath() {
        return basePath;
    }

    public List<ApiTestCaseConfig> getApiTestCaseConfigs() {
        return apiTestCaseConfigs;
    }
}
