package com.abm.restinteg.models.config;

import com.abm.restinteg.fileio.FileLoader;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class ExpectedResponse {

    @JsonProperty("body")
    private String responseFileLocation;

    @JsonProperty("status")
    private int statusCode;

    private FileLoader fileLoader;

    public ExpectedResponse() {
        fileLoader = new FileLoader();
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(responseFileLocation)
                .map(loc -> fileLoader.loadFile(loc, FileLoader.FileType.RESPONSE));
    }

    public Optional<String> getFileLocation() {
        return Optional.ofNullable(responseFileLocation);
    }

    public int getStatusCode() {
        return statusCode;
    }

}
