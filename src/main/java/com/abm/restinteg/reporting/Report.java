package com.abm.restinteg.reporting;

import com.abm.restinteg.models.TestResult;
import com.abm.restinteg.models.config.RestIntegration;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Report {

    private static final String RESOURCE_LOCATION = "reporting/";
    public static final String HTML_REPORT = "html-report.vm";
    private VelocityEngine velocityEngine;
    private RestIntegration restIntegration;
    private VelocityContext velocityContext;

    public Report(RestIntegration restIntegration) {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        this.restIntegration = restIntegration;
        velocityContext = new VelocityContext();
        buildContext();
    }

    public void buildContext() {
        velocityContext.put("generatedOn", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a")));
        velocityContext.put("version", restIntegration.getVersion());
    }

    public String loadTemplate(String name, List<TestResult> results) {
        Template template = velocityEngine.getTemplate(name);
        velocityContext.put("results", results);
        velocityContext.put("resultMap", results.stream().collect(Collectors.groupingBy(TestResult::getApiName)));
        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);
        return writer.toString();
    }

    public void generate(List<TestResult> results) {
        String report = loadTemplate(RESOURCE_LOCATION + HTML_REPORT, results);
        try {
            Files.write(Paths.get(System.getProperty("user.home"), "Desktop","rest-integ-report.html"), report.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
