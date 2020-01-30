package com.abm.restinteg.core

import com.abm.restinteg.client.RestClient
import com.abm.restinteg.models.config.RestIntegrationConfig
import com.abm.restinteg.models.core.TestCase
import com.abm.restinteg.models.core.TestScenario
import com.abm.restinteg.reporting.Report
import spock.lang.Specification
import spock.lang.Subject

import static com.abm.restinteg.models.core.HttpMethod.POST

class TestCaseBuilderSpec extends Specification {

    String configFile = "rest-integ-merobo.yml"

    RestIntegrationConfig restIntegrationConfig

    TestScenarioBuilder mockTestScenarioBuilder

    @Subject
    TestCaseBuilder builder

    void setup() {
        0 * _
        mockTestScenarioBuilder = Mock()
        builder = new TestCaseBuilder(mockTestScenarioBuilder)
        ConfigFileLoader configFileLoader = new ConfigFileLoader(configFile)
        restIntegrationConfig = configFileLoader.load()
    }

    def "build - should build the test cases"() {
        given:
        def basePath = restIntegrationConfig.basePath
        def apiTestCaseConfig = restIntegrationConfig.apiTestCaseConfigs[0]
        def scenarioConfig = apiTestCaseConfig.testScenarioConfigs[0]
        def apiName = "Get Customer Details"
        def scenarioName = "Should fetch customer details successfully"
        Report mockReport = Mock()
        RestClient mockRestClient = Mock()
        TestScenario testScenario = new TestScenario(scenarioName, basePath, mockReport, mockRestClient, apiName)

        2 * mockTestScenarioBuilder.build(basePath, apiTestCaseConfig, _) >> testScenario

        when:
        List<TestCase> testCases = builder.build(restIntegrationConfig.basePath, restIntegrationConfig.apiTestCaseConfigs)

        then:
        testCases.size() == 1
        testCases[0].name == "Register User"
        testCases[0].path == "/auth"
        testCases[0].method == POST

        testCases[0].scenarios.size() == 2
    }
}
