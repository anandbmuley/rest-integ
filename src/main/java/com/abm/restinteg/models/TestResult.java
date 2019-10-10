package com.abm.restinteg.models;

public class TestResult {

    private String apiName;
    private String name;
    private Status status;
    private String message;
    private String expected;
    private String actual;

    public enum Status {
        PASSED, FAILED
    }

    private TestResult(String apiName, String name, Status status, String message, String expected, String actual) {
        this.apiName = apiName;
        this.name = name;
        this.status = status;
        this.message = message;
        this.expected = expected;
        this.actual = actual;
    }

    public static TestResult createSuccess(String apiName, String name, String expected, String actual) {
        return new TestResult(apiName, name, Status.PASSED, "Test Passed Successfully !", expected, actual);
    }

    public static TestResult createFailure(String apiName, String name, String details, String expected, String actual) {
        return new TestResult(apiName, name, Status.FAILED, details, expected, actual);
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

    public String getActual() {
        return actual;
    }

    public String getExpected() {
        return expected;
    }
}
