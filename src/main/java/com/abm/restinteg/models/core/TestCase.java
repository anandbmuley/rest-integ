package com.abm.restinteg.models.core;

import java.util.LinkedList;
import java.util.List;

public class TestCase {

    private final String name;
    private final String path;
    private final HttpMethod method;
    private final List<TestScenario> scenarios;

    public TestCase(String name, String path, HttpMethod method) {
        this.name = name;
        this.path = path;
        this.method = method;
        scenarios = new LinkedList<>();
    }

    public void add(TestScenario testScenario) {
        scenarios.add(testScenario);
    }

    public List<TestScenario> getScenarios() {
        return scenarios;
    }

    public void run() {
        scenarios.forEach(TestScenario::run);
    }

}
