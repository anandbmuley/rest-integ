package com.abm.restinteg.core

import com.abm.restinteg.models.config.ApiTestCaseConfig
import com.abm.restinteg.models.config.RestIntegrationConfig
import com.abm.restinteg.models.config.TestScenarioConfig
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.runtime.RuntimeConstants
import spock.lang.Specification

class RestIntegBeansConfigSpec extends Specification {

    RestIntegBeansConfig beansConfig

    ConfigFileLoader mockConfigFileLoader
    RestIntegrationConfig mockRestIntegration

    void setup() {
        0 * _

        mockRestIntegration = Mock(RestIntegrationConfig)
        mockConfigFileLoader = Mock(ConfigFileLoader)
        ApiTestCaseConfig apiTestCaseConfig = new ApiTestCaseConfig()
        apiTestCaseConfig.testScenarioConfigs = [new TestScenarioConfig()]

        1 * mockRestIntegration.getApiTestCaseConfigs() >> [apiTestCaseConfig]
        1 * mockRestIntegration.getBasePath() >> "http://localhost:8080"
        1 * mockConfigFileLoader.load() >> mockRestIntegration
        1 * mockRestIntegration.getVersion() >> "1.2.3"

        beansConfig = new RestIntegBeansConfig(mockConfigFileLoader)
    }

    def "configure - should configure all beans correctly"() {
        when:
        TestCaseRunner testCaseRunner = beansConfig.configure()

        then:
        testCaseRunner.restClient != null
        testCaseRunner.restIntegrationConfig != null
        testCaseRunner.report != null
        testCaseRunner.report.templateLoader != null
        VelocityContext context = testCaseRunner.report.templateLoader.velocityContext
        context != null
        context.get("generatedOn") != null
        context.get("version") == "1.2.3"

        VelocityEngine velocityEngine = testCaseRunner.report.templateLoader.velocityEngine
        velocityEngine != null
        velocityEngine.getProperty(RuntimeConstants.RESOURCE_LOADER).toString() == "[classpath]"
        velocityEngine.getProperty("classpath.resource.loader.class") == "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
    }
}
