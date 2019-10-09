package com.abm.restinteg.reporting;

import com.abm.restinteg.models.TestResult;

import java.util.List;
import java.util.stream.Collectors;

public class Report {

    public void generate(List<TestResult> results) {
        List<TestResult> anyFailedTest = results.stream().filter(testResult -> "FAILED".equalsIgnoreCase(testResult.getStatus())).collect(Collectors.toList());
        if (null != anyFailedTest && anyFailedTest.size() > 0) {
            System.err.println("INTEGRATION TEST FAILED");
            System.err.println("FAILED TEST CASES ARE :");
            anyFailedTest.forEach(testResult -> {
                System.err.println("NAME : " + testResult.getName());
                System.err.println(testResult.getMessage());
            });
        }
    }

}
