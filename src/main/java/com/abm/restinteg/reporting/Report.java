package com.abm.restinteg.reporting;

import com.abm.restinteg.models.core.TestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Report {

    private static final String RESOURCE_LOCATION = "reporting/";
    private static final String HTML_REPORT = "html-report.vm";
    private static final String REPORT_NAME = "rest-integ-report.html";
    private final TemplateLoader templateLoader;

    public Report(TemplateLoader templateLoader) {
        this.templateLoader = templateLoader;
    }

    public void generate(List<TestResult> results) {
        String report = templateLoader.load(RESOURCE_LOCATION + HTML_REPORT, results);
        try {
            Files.write(Paths.get(System.getProperty("user.home"), "Desktop", REPORT_NAME), report.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
