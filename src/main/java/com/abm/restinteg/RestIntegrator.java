package com.abm.restinteg;

import com.abm.restinteg.models.RestIntegConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RestIntegrator {

    private ObjectMapper mapper;
    private RestIntegConfig restIntegConfig;
    private final String DEFAULT_CONFIG_NAME = "rest-integ.yml";
    private String configFileName = DEFAULT_CONFIG_NAME;

    private TestCaseRunner testCaseRunner;

    public RestIntegrator() throws Exception {
        init();
    }

    private void init() throws Exception {
        mapper = new ObjectMapper(new YAMLFactory());
        testCaseRunner = new TestCaseRunner();
        loadConfig();
        invokeTests();
    }

    private void invokeTests() throws Exception {
        testCaseRunner.invokeTests(restIntegConfig);
    }

    public RestIntegrator(String configFileName) throws Exception {
        if (!StringUtils.isEmpty(configFileName)) {
            this.configFileName = configFileName;
        }
        init();
    }

    private void loadConfig() {
        try {
            restIntegConfig = mapper.readValue(new File("src/main/resources/" + configFileName), RestIntegConfig.class);
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
