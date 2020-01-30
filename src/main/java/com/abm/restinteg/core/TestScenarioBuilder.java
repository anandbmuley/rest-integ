package com.abm.restinteg.core;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.config.ApiTestCaseConfig;
import com.abm.restinteg.models.config.ExpectedResponseConfig;
import com.abm.restinteg.models.config.TestScenarioConfig;
import com.abm.restinteg.models.core.ExpectedResponse;
import com.abm.restinteg.models.core.TestScenario;
import com.abm.restinteg.reporting.Report;

import java.util.Optional;

public class TestScenarioBuilder {

    private final Report report;
    private final RestClient restClient;

    public TestScenarioBuilder(Report report, RestClient restClient) {
        this.report = report;
        this.restClient = restClient;
    }

    public TestScenario build(String basePath, ApiTestCaseConfig apiTestCaseConfig, TestScenarioConfig testScenarioConfig) {
        TestScenario testScenario = new TestScenario(testScenarioConfig.getName(), basePath, report, restClient, apiTestCaseConfig.getName());
        testScenario.setUrl(apiTestCaseConfig.getPath());
        testScenario.setHttpMethod(apiTestCaseConfig.getHttpMethod());
        Optional.ofNullable(testScenarioConfig.getRequestConfig())
                .ifPresent(sampleRequest -> {
                    sampleRequest.getBody().ifPresent(testScenario::setRequestBody);
                    testScenario.setResponseListData(sampleRequest.isList());
                    testScenario.setPathVariables(sampleRequest.getPathParams());
                    ExpectedResponseConfig expectedResponseConfig = testScenarioConfig.getExpectedResponseConfig();
                    ExpectedResponse expectedResponse = expectedResponseConfig.getBody().map(responseBody ->
                            new ExpectedResponse(expectedResponseConfig.getStatusCode(), responseBody)
                    ).orElse(new ExpectedResponse(expectedResponseConfig.getStatusCode()));
                    testScenario.setExpectedResponse(expectedResponse);
                });
        return testScenario;
    }

}
