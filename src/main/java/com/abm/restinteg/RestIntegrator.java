package com.abm.restinteg;

import com.abm.restinteg.client.RestClient;
import com.abm.restinteg.models.config.RestIntegration;
import com.abm.restinteg.reporting.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RestIntegrator {

    private ObjectMapper mapper;
    private RestIntegration restIntegration;
    private final String DEFAULT_CONFIG_NAME = "rest-integ.yml";
    private String configFileName = DEFAULT_CONFIG_NAME;

    private TestCaseRunner testCaseRunner;

    public RestIntegrator() throws Exception {
        init();
    }

    private void init() {
        mapper = new ObjectMapper(new YAMLFactory());
        loadConfig();
        configureBeans();
        try {
            invokeTests();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    private void invokeTests() throws Exception {
        testCaseRunner.invokeTests();
    }

    public RestIntegrator(String configFileName) throws Exception {
        if (!StringUtils.isEmpty(configFileName)) {
            this.configFileName = configFileName;
        }
        init();
    }

    private void configureBeans() {
        RestTemplate restTemplate = new RestTemplate();
        RestClient restClient = new RestClient(restTemplate);
        testCaseRunner = new TestCaseRunner(restClient, restIntegration, new Report(restIntegration));
    }

    private void loadConfig() {
        try {
            restIntegration = mapper.readValue(new File("src/test/resources/" + configFileName), RestIntegration.class);
        } catch (FileNotFoundException fnf) {
            System.err.println("Config file not found : " + configFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        RestIntegrator restIntegrator = new RestIntegrator();
    }

}
