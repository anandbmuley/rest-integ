package com.abm.restinteg.core

import com.abm.restinteg.models.config.ApiTestCaseConfig
import com.abm.restinteg.models.config.RestIntegrationConfig
import com.abm.restinteg.models.core.TestCase
import com.abm.restinteg.models.core.TestResult
import com.abm.restinteg.reporting.Report
import spock.lang.Specification
import spock.lang.Subject

class TestCaseRunnerSpec extends Specification {

    @Subject
    TestCaseRunner testCaseRunner

    def basePath = "http://localhost:8080"

    RestIntegrationConfig mockRestIntegrationConfig
    TestCaseBuilder mockTestCaseBuilder
    TestCase mockTestCase
    Report mockReport
    TestResult mockTestResult

    void setup() {
        mockTestResult = Mock()
        mockReport = Mock()
        mockTestCase = Mock()
        mockTestCaseBuilder = Mock()
        mockRestIntegrationConfig = Mock()
        prepareCommonMocks()
    }

    def prepareCommonMocks() {
        1 * mockRestIntegrationConfig.getBasePath() >> basePath
        1 * mockRestIntegrationConfig.getApiTestCaseConfigs() >> [new ApiTestCaseConfig()]
    }

    def "prepareTestCases - should build test cases from config"() {
        when:
        new TestCaseRunner(mockRestIntegrationConfig, mockTestCaseBuilder, mockReport)

        then:
        1 * mockTestCaseBuilder.build(basePath, { List<ApiTestCaseConfig> it ->
            assert it.size() == 1
            true
        }) >> [mockTestCase]
    }

    def "invokeTests - should invoke testcases run method"() {
        given:
        1 * mockTestCaseBuilder.build(basePath, _) >> [mockTestCase]
        testCaseRunner = new TestCaseRunner(mockRestIntegrationConfig, mockTestCaseBuilder, mockReport)

        when:
        testCaseRunner.invokeTests()

        then:
        1 * mockTestCase.run()
    }
}
