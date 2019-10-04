package com.abm.restinteg;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.TestResult;
import com.abm.restinteg.models.config.RestIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                } catch (Exception e) {
//                    e.printStackTrace();
                    testResults.add(new TestResult(apiDetails.getName(), "FAILED"));
                }

            });
        });
        Optional<TestResult> anyFailedTest = testResults.stream().filter(testResult -> "FAILED".equalsIgnoreCase(testResult.getStatus())).findAny();
        if (anyFailedTest.isPresent()) {
//            System.out.println("FAILED TEST CASE : " + anyFailedTest.get().getName());
            throw new Exception("INTEGRATION TEST FAILED !");
        }
    }

}
