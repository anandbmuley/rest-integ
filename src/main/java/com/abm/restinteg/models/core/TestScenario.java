package com.abm.restinteg.models.core;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.reporting.Report;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestScenario {

    private String apiName;
    private String name;
    private String url;
    private final String baseUrl;
    private String requestBody;
    private HttpMethod httpMethod;
    private boolean responseListData;
    private Map<String, Object> pathVariables;
    private ExpectedResponse expectedResponse;
    private RestClient restClient;

    private Report report;

    private List<TestResult> testResults;


    public TestScenario(String name, String baseUrl, Report report, RestClient restClient, String apiName) {
        this.name = name;
        this.baseUrl = baseUrl;
        testResults = new ArrayList<>();
        this.restClient = restClient;
        this.report = report;
        this.apiName = apiName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        requestBody = null;
        responseListData = false;
    }

    public void setExpectedResponse(ExpectedResponse expectedResponse) {
        this.expectedResponse = expectedResponse;
    }

    public void setPathVariables(Map<String, Object> pathVariables) {
        this.pathVariables = pathVariables;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void setResponseListData(boolean responseListData) {
        this.responseListData = responseListData;
    }

    public boolean isResponseListData() {
        return responseListData;
    }

    public Map<String, Object> getPathVariables() {
        return pathVariables;
    }

    public String getCompleteUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(baseUrl).append(url);
        return stringBuilder.toString();
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void run() {
        try {
            restClient.call(this).validate(expectedResponse);
            testResults.add(TestResult.createSuccess(apiName, name, expectedResponse.getBody().orElse(null)));
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == expectedResponse.getStatusCode()) {
                testResults.add(TestResult.createSuccess(apiName, name, expectedResponse.getBody().orElse(null)));
            } else {
                testResults.add(TestResult.createFailure(apiName, name, e.getResponseBodyAsString(), expectedResponse.getBody().orElse(null)));
            }
        } catch (Throwable e) {
            testResults.add(TestResult.createFailure(apiName, name, e.getMessage(), expectedResponse.getBody().orElse(null)));
        }

        report.generate(testResults);
    }

}
