package com.abm.restinteg.reporting;

import com.abm.restinteg.models.TestResult;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Report {

    private static final String RESOURCE_LOCATION = "src/main/resources/reporting/";
    public static final String HTML_REPORT = "html-report.vm";
    private VelocityEngine velocityEngine;
    private String EXPORT_LOCATION = "/home/anandmuley/Desktop/htmlreport.html";

    public Report() {
        velocityEngine = new VelocityEngine();
        velocityEngine.init();
    }

    public String loadTemplate(String name, List<TestResult> results) {
        Template template = velocityEngine.getTemplate(RESOURCE_LOCATION + name);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("results", results);
        velocityContext.put("resultMap",results.stream().collect(Collectors.groupingBy(TestResult::getApiName)));
        velocityContext.put("generatedOn", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a")));
        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);
        return writer.toString();
    }

    public void generate(List<TestResult> results) {
        String report = loadTemplate(HTML_REPORT, results);
        try {
            Files.write(Paths.get(EXPORT_LOCATION), report.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
