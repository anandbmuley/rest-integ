package com.abm.restinteg.models;

public class TestResult {

    private String name;
    private String status;
    private String message;

    public TestResult(String name, String status, String message) {
        this.name = name;
        this.status = status;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
