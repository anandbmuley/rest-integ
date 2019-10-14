package com.abm.restinteg.core;

import com.abm.restinteg.models.config.RestIntegration;
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

    public RestIntegration load() {
        RestIntegration restIntegration = null;
        try {
            restIntegration = mapper.readValue(new File("src/test/resources/" + configFileName), RestIntegration.class);
        } catch (FileNotFoundException fnf) {
            System.err.println("Config file not found : " + configFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restIntegration;
    }

}
