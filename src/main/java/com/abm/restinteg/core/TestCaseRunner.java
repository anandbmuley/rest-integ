package com.abm.restinteg.core;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.config.RestIntegrationConfig;
import com.abm.restinteg.models.core.TestCase;
import com.abm.restinteg.models.core.TestResult;
import com.abm.restinteg.reporting.Report;

import java.util.ArrayList;
import java.util.List;

public class TestCaseRunner {

    private final RestClient restClient;
    private List<TestResult> testResults;
    private List<TestCase> testCases;
    private Report report;
    private RestIntegrationConfig restIntegrationConfig;
    private TestCaseBuilder testCaseBuilder;

    public TestCaseRunner(RestClient restClient, RestIntegrationConfig restIntegrationConfig, Report report) {
        this.restClient = restClient;
        testResults = new ArrayList<>();
        this.report = report;
        this.restIntegrationConfig = restIntegrationConfig;
        testCaseBuilder = new TestCaseBuilder(new TestScenarioBuilder(report, restClient));
        prepareTestCases();
    }

    public void prepareTestCases() {
        testCases = testCaseBuilder.build(restIntegrationConfig.getBasePath(), restIntegrationConfig.getApiTestCaseConfigs());
    }

    public void invokeTests() {
        testCases.forEach(TestCase::run);
    }

}
