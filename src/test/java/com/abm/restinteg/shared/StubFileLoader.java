package com.abm.restinteg.shared;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StubFileLoader {

    private String getFileContent(String filename) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        Path resourcePath = Paths.get(resource.toURI());
        return String.join("", Files.readAllLines(resourcePath));
    }

    public String getRequestFile(String filename) throws IOException, URISyntaxException {
        return getFileContent("requests/" + filename);
    }

    public String getResponseFile(String filename) throws IOException, URISyntaxException {
        return getFileContent("responses/" + filename);
    }

}
