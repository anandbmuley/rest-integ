package com.abm.restinteg.models.core;

public class TestResult {

    private String apiName;
    private String name;
    private Status status;
    private String message;
    private String expectedJson;

    public enum Status {
        PASSED, FAILED
    }

    private TestResult(String apiName, String name, Status status, String message, String expectedJson) {
        this.apiName = apiName;
        this.name = name;
        this.status = status;
        this.message = message;
        this.expectedJson = expectedJson;
    }

    public static TestResult createSuccess(String apiName, String name) {
        return new TestResult(apiName, name, Status.PASSED, "Test Passed Successfully !", null);
    }

    public static TestResult createFailure(String apiName, String name, String details, String expectedJson) {
        return new TestResult(apiName, name, Status.FAILED, details, expectedJson);
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getApiName() {
        return apiName;
    }

    public String getExpectedJson() {
        return expectedJson;
    }
}
