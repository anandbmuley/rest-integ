package com.abm.restinteg.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileLoader {

    private final String BASE_LOCATION = "src/test/resources/";

    public enum FileType {
        REQUEST("requests/"), RESPONSE("responses/");

        private String location;

        FileType(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }
    }

    public String loadFile(String fileLocation, FileType fileType) {
        String actual = "";
        try {
            actual = Files.readAllLines(Paths.get(BASE_LOCATION + fileType.getLocation() + fileLocation)).stream().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actual;
    }

}
