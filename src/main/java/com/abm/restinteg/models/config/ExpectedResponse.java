package com.abm.restinteg.models.config;

import com.abm.restinteg.fileio.FileLoader;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpectedResponse {

    @JsonProperty("body")
    private String responseFileLocation;

    @JsonProperty("status")
    private int statusCode;

    private FileLoader fileLoader;

    public ExpectedResponse() {
        fileLoader = new FileLoader();
    }

    public String getBody() {
        return fileLoader.loadFile(responseFileLocation, FileLoader.FileType.RESPONSE);
    }

    public int getStatusCode() {
        return statusCode;
    }

}
