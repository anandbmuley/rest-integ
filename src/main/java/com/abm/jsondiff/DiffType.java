package com.abm.jsondiff;

public enum DiffType {

    MISSING_FIELD_101("Expected field not present in Actual Data"),
    MISSING_FIELD_102("Un-expected field present in Actual Data"),
    VALUE_MISMATCH_101("Expected value did not match with actual");

    private String message;

    DiffType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
