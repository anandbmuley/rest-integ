package com.abm.restinteg.client

import com.abm.restinteg.models.core.ApiResponse
import com.abm.restinteg.models.core.TestScenario
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class DeleteRequestSpec extends Specification {

    @Subject
    DeleteRequest deleteRequest

    String basePath = "http://localhost:8080"
    RestTemplate mockRestTemplate
    TestScenario mockTestScenario

    void setup() {
        mockRestTemplate = Mock()
        mockTestScenario = Mock()
        deleteRequest = new DeleteRequest(basePath, mockRestTemplate)
    }

    @Unroll
    def "call - should call delete method #URI_VARIABLES"() {
        given:
        Map<String, Object> uriVariables = ["Username": "ABC"]
        1 * mockTestScenario.getPathVariables() >> uriVariables
        1 * mockRestTemplate.delete(basePath, uriVariables)

        when:
        ResponseEntity<ApiResponse> actual = deleteRequest.call(mockTestScenario)

        then:
        !actual.body
        actual.statusCode == HttpStatus.OK

        where:
        URI_VARIABLES       || _
        ["Username": "ABC"] || _
        []                  || _
        null                || _
    }
}
