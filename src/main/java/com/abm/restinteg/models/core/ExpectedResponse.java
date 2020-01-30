package com.abm.restinteg.models.core;

import java.util.Optional;

public class ExpectedResponse {

    private String body;
    private final int statusCode;

    public ExpectedResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public ExpectedResponse(int statusCode, String body) {
        this.body = body;
        this.statusCode = statusCode;
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(body);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
