package com.abm.restinteg.models.config;

import com.abm.restinteg.fileio.FileLoader;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;

public class SampleRequest {

    @JsonProperty("body")
    private String requestFileLocation;

    @JsonProperty("pathParams")
    private Map<String, Object> pathParams;

    @JsonProperty("responseIsList")
    private boolean list;

    private FileLoader fileLoader;


    public SampleRequest() {
        fileLoader = new FileLoader();
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(requestFileLocation)
                .map($ -> fileLoader.loadFile(requestFileLocation, FileLoader.FileType.REQUEST));
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public boolean isList() {
        return list;
    }
}
