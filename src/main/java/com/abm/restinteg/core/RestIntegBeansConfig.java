package com.abm.restinteg.core;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.config.RestIntegration;
import com.abm.restinteg.reporting.Report;
import com.abm.restinteg.reporting.TemplateLoader;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.velocity.runtime.RuntimeConstants.RESOURCE_LOADER;

public class RestIntegBeansConfig {

    private final RestIntegration restIntegration;

    public RestIntegBeansConfig(ConfigFileLoader configFileLoader) {
        this.restIntegration = configFileLoader.load();
    }

    public TestCaseRunner configure() {
        RestTemplate restTemplate = new RestTemplate();
        RestClient restClient = new RestClient(restTemplate);
        VelocityContext velocityContext = buildContext();
        VelocityEngine velocityEngine = configureVelocityEngine();
        TemplateLoader templateLoader = new TemplateLoader(velocityContext, velocityEngine);
        return new TestCaseRunner(restClient, restIntegration, new Report(templateLoader));
    }

    private VelocityContext buildContext() {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("generatedOn", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a")));
        velocityContext.put("version", restIntegration.getVersion());
        return velocityContext;
    }

    private VelocityEngine configureVelocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        return velocityEngine;
    }


}
