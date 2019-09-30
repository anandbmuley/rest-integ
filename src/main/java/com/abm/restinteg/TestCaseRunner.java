package com.abm.restinteg;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.RestIntegConfig;
import com.abm.restinteg.models.TestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCaseRunner {

    private RestClient restClient;
    private List<TestResult> testResults;

    public TestCaseRunner() {
        restClient = new RestClient();
        testResults = new ArrayList<>();
    }

    public void invokeTests(RestIntegConfig restIntegConfig) throws Exception {
        restIntegConfig.getApiDetails().forEach(apiDetails -> {
            apiDetails.getTestCases().forEach(testCaseConfig -> {
                try {
                    restClient.call(restIntegConfig.getBasePath() + apiDetails.getPath())
                            .validate(testCaseConfig.getExpectedResponse());
                } catch (Exception e) {
                    testResults.add(new TestResult(apiDetails.getName(), "FAILED"));
                }
            });
        });
        Optional<TestResult> anyFailedTest = testResults.stream().filter(testResult -> "FAILED".equalsIgnoreCase(testResult.getStatus())).findAny();
        if (anyFailedTest.isPresent()) {
            throw new Exception("INTEGRATION TEST FAILED !");
        }
    }

}
