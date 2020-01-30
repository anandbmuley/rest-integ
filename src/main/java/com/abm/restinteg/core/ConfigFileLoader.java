package com.abm.restinteg.core;

import com.abm.restinteg.models.config.RestIntegrationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigFileLoader {

    private final String DEFAULT_CONFIG_NAME = "rest-integ.yml";
    private String configFileName = DEFAULT_CONFIG_NAME;

    private ObjectMapper mapper;

    public ConfigFileLoader() {
        mapper = new ObjectMapper(new YAMLFactory());
    }

    public ConfigFileLoader(String configFileName) {
        this();
        this.configFileName = configFileName;
    }

    public RestIntegrationConfig load() throws IOException {
        RestIntegrationConfig restIntegrationConfig;
        try {
            restIntegrationConfig = mapper.readValue(new File("src/test/resources/" + configFileName), RestIntegrationConfig.class);
        } catch (FileNotFoundException fnf) {
            throw new FileNotFoundException("Config file not found : " + configFileName);
        }
        return restIntegrationConfig;
    }

}
