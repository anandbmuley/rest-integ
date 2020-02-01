package com.abm.restinteg.client

import com.abm.restinteg.models.core.ApiResponse
import com.abm.restinteg.models.core.HttpMethod
import com.abm.restinteg.models.core.TestScenario
import com.abm.restinteg.reporting.Report
import com.abm.restinteg.validators.ResponseValidator
import org.springframework.http.ResponseEntity
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

class RestClientSpec extends Specification {

    @Subject
    RestClient restClient

    RestTemplate mockRestTemplate
    ResponseEntity<ApiResponse> mockResponseEntity
    ResponseValidator mockResponseValidator

    def setup() {
        0 * _
        mockResponseValidator = Mock()
        mockResponseEntity = Mock()
        mockRestTemplate = Mock()
        restClient = new RestClient(mockRestTemplate, mockResponseValidator)
    }

    def "call - should run the test scenario"() {
        given:
        Report mockReport = Mock()
        RestClient mockRestClient = Mock()
        def basePath = "http://localhost:8080"
        def name = "Should get the customer details successfully"
        def apiName = "Get Customer Details"
        TestScenario scenario = new TestScenario(name, basePath, mockReport, mockRestClient, apiName)
        scenario.setUrl("/path")
        scenario.setHttpMethod(HttpMethod.GET)
        1 * mockRestTemplate.getForEntity("$basePath/path", String, [:]) >> ResponseEntity.ok().build()

        when:
        RestClient actual = restClient.call(scenario)

        then:
        actual.restTemplate != null
    }

    def "Validate"() {
    }

    def "GetApiResponseResponseEntity"() {
    }
}
