package com.abm.restinteg.models.config;

import com.abm.restinteg.fileio.FileLoader;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SampleRequest {

    @JsonProperty("body")
    private String requestFileLocation;

    @JsonProperty("responseIsList")
    private boolean list;

    private FileLoader fileLoader;


    public SampleRequest() {
        fileLoader = new FileLoader();
    }

    public String getBody() {
        return fileLoader.loadFile(requestFileLocation, FileLoader.FileType.REQUEST);
    }

    public boolean isList() {
        return list;
    }
}
