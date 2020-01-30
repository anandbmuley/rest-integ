package com.abm.restinteg.models.core;

public class TestResult {

    private String apiName;
    private String name;
    private Status status;
    private String message;
    private String expected;

    public enum Status {
        PASSED, FAILED
    }

    private TestResult(String apiName, String name, Status status, String message, String expected) {
        this.apiName = apiName;
        this.name = name;
        this.status = status;
        this.message = message;
        this.expected = expected;
    }

    public static TestResult createSuccess(String apiName, String name, String expected) {
        return new TestResult(apiName, name, Status.PASSED, "Test Passed Successfully !", expected);
    }

    public static TestResult createFailure(String apiName, String name, String details, String expected) {
        return new TestResult(apiName, name, Status.FAILED, details, expected);
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

    public String getExpected() {
        return expected;
    }
}
