package com.abm.restinteg.core

import com.abm.restinteg.models.config.RestIntegration
import spock.lang.Specification

class ConfigFileLoaderSpec extends Specification {

    ConfigFileLoader configFileLoader

    void setup() {
        configFileLoader = new ConfigFileLoader()
    }

    def "load - should load the config file"() {
        when:
        RestIntegration restIntegration = configFileLoader.load()

        then:
        restIntegration.serviceName == "REST Integrator"
        restIntegration.basePath == "http://localhost:8080/home-automation/api"
    }
}
