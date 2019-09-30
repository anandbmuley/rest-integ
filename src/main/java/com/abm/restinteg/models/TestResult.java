package com.abm.restinteg.models;

public class TestResult {

    private String name;
    private String status;

    public TestResult(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
