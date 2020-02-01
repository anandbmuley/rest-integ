package com.abm.restinteg.client

import com.abm.restinteg.models.core.ApiResponse
import com.abm.restinteg.models.core.TestScenario
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

class PostRequestSpec extends Specification {

    @Subject
    PostRequest postRequest

    def basePath = "http://localhost:8080"
    RestTemplate mockRestTemplate

    void setup() {
        mockRestTemplate = Mock()
        postRequest = new PostRequest(basePath, mockRestTemplate)
    }

    def "call - should call PostRequest"() {
        given:
        TestScenario mockTestScenario = Mock()
        def sampleBody = "Some data"
        1 * mockTestScenario.getRequestBody() >> sampleBody
        1 * mockRestTemplate.postForEntity(basePath, { HttpEntity<String> it ->
            assert it.body == sampleBody
            assert it.headers.size() == 1
            assert it.headers.getContentType() == MediaType.APPLICATION_JSON
            true
        }, ApiResponse) >> ResponseEntity.ok().build()

        when:
        ResponseEntity<ApiResponse> actual = postRequest.call(mockTestScenario)

        then:
        actual.statusCode == HttpStatus.OK
    }
}