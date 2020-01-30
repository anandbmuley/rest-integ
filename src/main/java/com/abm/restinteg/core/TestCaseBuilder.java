package com.abm.restinteg.core;

import com.abm.restinteg.models.config.ApiTestCaseConfig;
import com.abm.restinteg.models.core.TestCase;
import com.abm.restinteg.models.core.TestScenario;

import java.util.LinkedList;
import java.util.List;

public class TestCaseBuilder {

    private final TestScenarioBuilder testScenarioBuilder;

    public TestCaseBuilder(TestScenarioBuilder testScenarioBuilder) {
        this.testScenarioBuilder = testScenarioBuilder;
    }

    public List<TestCase> build(String basePath, List<ApiTestCaseConfig> apiTestCaseConfigs) {
        List<TestCase> testCases = new LinkedList<>();
        apiTestCaseConfigs.forEach(apiTestCaseConfig -> {
            TestCase testCase = new TestCase(apiTestCaseConfig.getName(), apiTestCaseConfig.getPath(), apiTestCaseConfig.getHttpMethod());
            apiTestCaseConfig.getTestScenarioConfigs()
                    .forEach(testScenarioConfig -> {
                        TestScenario testScenario = testScenarioBuilder.build(basePath, apiTestCaseConfig, testScenarioConfig);
                        testCase.add(testScenario);
                    });
            testCases.add(testCase);
        });
        return testCases;
    }

}
