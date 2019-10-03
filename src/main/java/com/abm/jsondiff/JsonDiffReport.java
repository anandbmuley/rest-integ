package com.abm.jsondiff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonDiffReport {

    private Map<String, Object> leftDiff;
    private Map<String, Object> rightDiff;

    private HtmlReport htmlReport;

    public JsonDiffReport() {
        htmlReport = new HtmlReport();
        leftDiff = new LinkedHashMap<>();
        rightDiff = new LinkedHashMap<>();
    }

    public void left(String key, Object val) {
        leftDiff.put(key, val);
    }

    public void right(String key, Object val) {
        rightDiff.put(key, val);
    }

    public void leftDiff(String key, Object val) {
        leftDiff.put(key + "->", val);
    }

    public void rightDiff(String key, Object val) {
        rightDiff.put(key + "->", val);
    }

    public void asHtml() {
        String report = htmlReport.print(leftDiff, rightDiff);
        try {
            Files.write(Paths.get("/home/anandmuley/Desktop/generated.html"), report.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
