package com.abm.restinteg.models.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCase {

    private final String name;
    private final String path;
    private final HttpMethod method;
    private final List<TestScenario> scenarios;
    private List<TestResult> testResults;

    public TestCase(String name, String path, HttpMethod method) {
        this.name = name;
        this.path = path;
        this.method = method;
        scenarios = new LinkedList<>();
        testResults = new LinkedList<>();
    }

    public void add(TestScenario testScenario) {
        scenarios.add(testScenario);
    }

    public List<TestScenario> getScenarios() {
        return scenarios;
    }

    public void run() {
        scenarios.forEach(TestScenario::run);
        testResults = scenarios.stream().map(TestScenario::getTestResult).collect(Collectors.toList());
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }
}
