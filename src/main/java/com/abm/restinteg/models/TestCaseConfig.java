package com.abm.restinteg.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TestCaseConfig {

    @JsonProperty("name")
    private String name;

    @JsonProperty("response")
    private String responseFileLocation;

    public String getName() {
        return name;
    }

    public String getExpectedResponse() {
        String actual = "";
        try {
            actual = Files.readAllLines(Paths.get("src/main/resources/responses/" + responseFileLocation)).stream().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actual;
    }
}
