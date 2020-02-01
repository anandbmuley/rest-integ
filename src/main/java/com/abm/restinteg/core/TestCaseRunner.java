package com.abm.restinteg.core;

import com.abm.restinteg.models.config.RestIntegrationConfig;
import com.abm.restinteg.models.core.TestCase;

import java.util.List;

public class TestCaseRunner {

    private List<TestCase> testCases;
    private RestIntegrationConfig restIntegrationConfig;
    private TestCaseBuilder testCaseBuilder;

    public TestCaseRunner(RestIntegrationConfig restIntegrationConfig, TestCaseBuilder testCaseBuilder) {
        this.restIntegrationConfig = restIntegrationConfig;
        this.testCaseBuilder = testCaseBuilder;
        prepareTestCases();
    }

    private void prepareTestCases() {
        testCases = testCaseBuilder.build(restIntegrationConfig.getBasePath(), restIntegrationConfig.getApiTestCaseConfigs());
    }

    public void invokeTests() {
        testCases.forEach(TestCase::run);
    }

}
