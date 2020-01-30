package com.abm.restinteg.core

import com.abm.restinteg.client.RestClient
import com.abm.restinteg.models.config.ApiTestCaseConfig
import com.abm.restinteg.models.config.TestScenarioConfig
import com.abm.restinteg.models.core.HttpMethod
import com.abm.restinteg.models.core.TestScenario
import com.abm.restinteg.models.config.RestIntegrationConfig
import com.abm.restinteg.reporting.Report
import spock.lang.Specification

class TestScenarioBuilderSpec extends Specification {

    String configFile = "rest-integ-merobo.yml"
    RestIntegrationConfig restIntegration
    TestScenarioBuilder testScenarioBuilder

    Report mockReport
    RestClient mockRestClient

    void setup() {
        mockReport = Mock()
        mockRestClient = Mock()
        ConfigFileLoader configFileLoader = new ConfigFileLoader(configFile)
        restIntegration = configFileLoader.load()
        testScenarioBuilder = new TestScenarioBuilder(mockReport, mockRestClient)
    }

    def "build - should create list of api requests"() {
        given:
        String basePath = restIntegration.basePath
        ApiTestCaseConfig apiTestCaseConfigs = restIntegration.apiTestCaseConfigs[0]
        TestScenarioConfig testScenarioConfig = apiTestCaseConfigs.testScenarioConfigs[0]

        when:
        TestScenario actual = testScenarioBuilder.build(basePath, apiTestCaseConfigs, testScenarioConfig)

        then:
        actual.requestBody
        actual.completeUrl == "http://localhost:8080/merobo/api/auth"
        actual.httpMethod == HttpMethod.POST
        null == actual.pathVariables
        !actual.responseListData
        actual.url == "/auth"
    }
}
