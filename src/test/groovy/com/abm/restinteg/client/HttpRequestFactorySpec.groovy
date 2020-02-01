package com.abm.restinteg.client

import com.abm.restinteg.models.core.HttpMethod
import com.abm.restinteg.models.core.TestScenario
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static com.abm.restinteg.models.core.HttpMethod.DELETE
import static com.abm.restinteg.models.core.HttpMethod.GET
import static com.abm.restinteg.models.core.HttpMethod.POST
import static com.abm.restinteg.models.core.HttpMethod.PUT

class HttpRequestFactorySpec extends Specification {

    TestScenario mockTestScenario
    RestTemplate mockRestTemplate

    def setup() {
        mockTestScenario = Mock()
        mockRestTemplate = Mock()
    }

    def "get - should return GetRequest instance"() {
        given:
        1 * mockTestScenario.getHttpMethod() >> GET

        when:
        HttpRequest httpRequest = HttpRequestFactory.get(mockTestScenario, mockRestTemplate)

        then:
        httpRequest instanceof GetRequest
    }

    def "get - should return PostRequest instance"() {
        given:
        1 * mockTestScenario.getHttpMethod() >> POST

        when:
        HttpRequest httpRequest = HttpRequestFactory.get(mockTestScenario, mockRestTemplate)

        then:
        httpRequest instanceof PostRequest
    }

    def "get - should return DeleteRequest instance"() {
        given:
        1 * mockTestScenario.getHttpMethod() >> DELETE

        when:
        HttpRequest httpRequest = HttpRequestFactory.get(mockTestScenario, mockRestTemplate)

        then:
        httpRequest instanceof DeleteRequest
    }

    def "get - should return PutRequest instance"() {
        given:
        1 * mockTestScenario.getHttpMethod() >> PUT

        when:
        HttpRequest httpRequest = HttpRequestFactory.get(mockTestScenario, mockRestTemplate)

        then:
        httpRequest instanceof PutRequest
    }

}
