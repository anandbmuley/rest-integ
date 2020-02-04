package com.abm.restinteg.core;

import com.abm.restinteg.models.config.RestIntegrationConfig;
import com.abm.restinteg.models.core.TestCase;
import com.abm.restinteg.models.core.TestResult;
import com.abm.restinteg.reporting.Report;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaseRunner {

    private List<TestCase> testCases;
    private RestIntegrationConfig restIntegrationConfig;
    private TestCaseBuilder testCaseBuilder;
    private final Report report;

    public TestCaseRunner(RestIntegrationConfig restIntegrationConfig, TestCaseBuilder testCaseBuilder,
                          Report report) {
        this.restIntegrationConfig = restIntegrationConfig;
        this.testCaseBuilder = testCaseBuilder;
        this.report = report;
        prepareTestCases();
    }

    private void prepareTestCases() {
        testCases = testCaseBuilder.build(restIntegrationConfig.getBasePath(), restIntegrationConfig.getApiTestCaseConfigs());
    }

    public void invokeTests() {
        testCases.forEach(TestCase::run);
    }

    public void generateReport() {
        List<TestResult> results = testCases.stream()
                .map(TestCase::getTestResults)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        report.generate(results);
    }

}
