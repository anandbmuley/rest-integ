package com.abm.restinteg;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.TestResult;
import com.abm.restinteg.models.config.ExpectedResponse;
import com.abm.restinteg.models.config.RestIntegration;
import com.abm.restinteg.reporting.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestCaseRunner {

    private final RestClient restClient;
    private List<TestResult> testResults;
    private Report report;
    private RestIntegration restIntegration;

    public TestCaseRunner(RestClient restClient, RestIntegration restIntegration, Report report) {
        this.restClient = restClient;
        testResults = new ArrayList<>();
        this.report = report;
        this.restIntegration = restIntegration;
    }

    public void invokeTests() throws Exception {
        ApiRequest apiRequest = new ApiRequest(restIntegration.getBasePath());
        restIntegration.getApiDetails().forEach(apiDetails -> {
            apiRequest.setUrl(apiDetails.getPath());
            apiRequest.setHttpMethod(apiDetails.getHttpMethod());
            apiDetails.getTestCases().forEach(testCaseConfig -> {
                Optional.ofNullable(testCaseConfig.getSampleRequest())
                        .ifPresent(sampleRequest -> {
                            sampleRequest.getBody().ifPresent(apiRequest::setBody);
                            apiRequest.setResponseListData(sampleRequest.isList());
                            apiRequest.setPathVariables(sampleRequest.getPathParams());
                        });
                ExpectedResponse expectedResponse = testCaseConfig.getExpectedResponse();
                String expected = expectedResponse.getFileLocation().orElse(null);
                try {
                    restClient.call(apiRequest).validate(expectedResponse);
                    testResults.add(TestResult.createSuccess(apiDetails.getName(), testCaseConfig.getName(), expected));
                } catch (Throwable e) {
//                    e.printStackTrace();
                    testResults.add(TestResult.createFailure(apiDetails.getName(), testCaseConfig.getName(), e.getMessage(), expected));
                }

            });
        });

        report.generate(testResults);
    }

}
