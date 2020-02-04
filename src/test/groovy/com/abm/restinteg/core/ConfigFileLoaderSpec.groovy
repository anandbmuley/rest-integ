package com.abm.restinteg.core

import com.abm.restinteg.models.config.RestIntegrationConfig
import spock.lang.Specification

class ConfigFileLoaderSpec extends Specification {

    ConfigFileLoader configFileLoader

    void setup() {
        configFileLoader = new ConfigFileLoader()
    }

    def "load - should load the config file"() {
        when:
        RestIntegrationConfig restIntegration = configFileLoader.load()

        then:
        restIntegration.serviceName == "MEROBO Rest API Integration Testing"
        restIntegration.basePath == "http://localhost:8090/merobo/api"
    }

    def "load - should log an error if config file is not found"() {
        given:
        configFileLoader.configFileName = ""

        when:
        configFileLoader.load()

        then:
        def ex = thrown(FileNotFoundException)
        ex.message == "Config file not found : "
    }
}
