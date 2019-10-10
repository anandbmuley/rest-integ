package com.abm.restinteg;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import com.abm.restinteg.models.TestResult;
import com.abm.restinteg.models.TestResult.Status;
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
                            apiRequest.setPathVariables(sampleRequest.getPathParams());
                        });
                ExpectedResponse expectedResponse = testCaseConfig.getExpectedResponse();
                String actual;
                String expected = expectedResponse.getBody().orElse(null);
                try {
                    restClient.call(apiRequest).validate(expectedResponse);
                    actual = Optional.ofNullable(restClient.getApiResponseResponseEntity().getBody())
                            .map(ApiResponse::getData).orElse(null);
                    testResults.add(TestResult.createSuccess(apiDetails.getName(), testCaseConfig.getName(), expected, actual));
                } catch (Throwable e) {
//                    e.printStackTrace();
                    actual = Optional.ofNullable(restClient.getApiResponseResponseEntity().getBody())
                            .map(ApiResponse::getData).orElse(null);
                    testResults.add(TestResult.createFailure(apiDetails.getName(), testCaseConfig.getName(), e.getMessage(), expected, actual));
                }

            });
        });

        report.generate(testResults);
    }

}
