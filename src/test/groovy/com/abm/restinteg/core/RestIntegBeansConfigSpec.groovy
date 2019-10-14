package com.abm.restinteg.core

import com.abm.restinteg.models.config.RestIntegration
import org.apache.velocity.VelocityContext
import spock.lang.Specification

class RestIntegBeansConfigSpec extends Specification {

    RestIntegBeansConfig beansConfig

    ConfigFileLoader mockConfigFileLoader
    RestIntegration mockRestIntegration

    void setup() {
        0 * _

        mockRestIntegration = Mock(RestIntegration)
        mockConfigFileLoader = Mock(ConfigFileLoader)

        1 * mockConfigFileLoader.load() >> mockRestIntegration
        1 * mockRestIntegration.getVersion() >> "1.2.3"

        beansConfig = new RestIntegBeansConfig(mockConfigFileLoader)
    }

    def "configure - should configure all beans correctly"() {
        when:
        TestCaseRunner testCaseRunner = beansConfig.configure()

        then:
        testCaseRunner.restClient != null
        testCaseRunner.restIntegration != null
        testCaseRunner.report != null
        testCaseRunner.report.templateLoader != null
        VelocityContext context = testCaseRunner.report.templateLoader.velocityContext
        context != null
        context.get("generatedOn") != null
        context.get("version") == "1.2.3"

        testCaseRunner.report.templateLoader.velocityEngine != null
    }
}
