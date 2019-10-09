package com.abm.restinteg;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.TestResult;
import com.abm.restinteg.models.config.RestIntegration;
import com.abm.restinteg.reporting.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCaseRunner {

    private final RestClient restClient;
    private List<TestResult> testResults;
    private Report report;

    public TestCaseRunner(RestClient restClient) {
        this.restClient = restClient;
        testResults = new ArrayList<>();
        report = new Report();
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

        report.generate(testResults);
    }

}
