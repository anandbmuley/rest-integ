package com.abm.restinteg.reporting;

import com.abm.restinteg.models.TestResult;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

public class TemplateLoader {

    private final VelocityContext velocityContext;
    private final VelocityEngine velocityEngine;

    public TemplateLoader(VelocityContext velocityContext, VelocityEngine velocityEngine) {
        this.velocityContext = velocityContext;
        this.velocityEngine = velocityEngine;
    }

    public String load(String name, List<TestResult> results) {
        Template template = velocityEngine.getTemplate(name);
        velocityContext.put("results", results);
        velocityContext.put("resultMap", results.stream().collect(Collectors.groupingBy(TestResult::getApiName)));
        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);
        return writer.toString();
    }

}
