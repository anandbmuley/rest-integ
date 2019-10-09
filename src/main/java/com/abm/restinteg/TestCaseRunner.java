package com.abm.restinteg;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.TestResult;
import com.abm.restinteg.models.config.RestIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestCaseRunner {

    private final RestClient restClient;
    private List<TestResult> testResults;

    public TestCaseRunner(RestClient restClient) {
        this.restClient = restClient;
        testResults = new ArrayList<>();
    }

    public void invokeTests(RestIntegration restIntegration) throws Exception {
        ApiRequest apiRequest = new ApiRequest(restIntegration.getBasePath());
        restIntegration.getApiDetails().forEach(apiDetails -> {
            apiRequest.setUrl(apiDetails.getPath());
            apiRequest.setHttpMethod(apiDetails.getHttpMethod());
            apiDetails.getTestCases().forEach(testCaseConfig -> {
                Optional.ofNullable(testCaseConfig.getSampleRequest())
                        .ifPresent(sampleRequest -> {
                            sampleRequest.getBody().ifPresent(apiRequest::setBody);
                            apiRequest.setResponseListData(sampleRequest.isList());
                        });
                try {
                    restClient.call(apiRequest).validate(testCaseConfig.getExpectedResponse());
                } catch (Throwable e) {
                    testResults.add(new TestResult(apiDetails.getName(), "FAILED", e.getMessage()));
                }

            });
        });
        List<TestResult> anyFailedTest = testResults.stream().filter(testResult -> "FAILED".equalsIgnoreCase(testResult.getStatus())).collect(Collectors.toList());
        if (null != anyFailedTest && anyFailedTest.size() > 0) {
            System.out.println("FAILED TEST CASES ARE :");
            anyFailedTest.forEach(testResult -> {
                System.err.println("NAME : " + testResult.getName());
                System.err.println(testResult.getMessage());
            });
            throw new Exception("INTEGRATION TEST FAILED !");
        }
    }

}
