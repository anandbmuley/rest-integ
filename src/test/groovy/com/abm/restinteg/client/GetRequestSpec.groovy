package com.abm.restinteg.client

import com.abm.restinteg.models.core.ApiResponse
import com.abm.restinteg.models.core.TestScenario
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class GetRequestSpec extends Specification {

    @Subject
    GetRequest getRequest

    String basePath = "http://localhost:8080"
    RestTemplate mockRestTemplate
    TestScenario mockTestScenario

    void setup() {
        mockRestTemplate = Mock()
        mockTestScenario = Mock()
        getRequest = new GetRequest(basePath, mockRestTemplate)
    }

    @Unroll
    def "call - should call get method #URI_VARIABLES"() {
        given:
        Map<String, Object> uriVariables = URI_VARIABLES
        1 * mockTestScenario.getPathVariables() >> uriVariables
        def responseData = "Data Content"
        ResponseEntity<ApiResponse> responseEntity = ResponseEntity.status(HttpStatus.OK)
                .headers(new HttpHeaders([] as MultiValueMap<String, String>)).body(responseData)
        1 * mockRestTemplate.getForEntity(basePath, String.class, uriVariables) >> responseEntity

        when:
        ResponseEntity<ApiResponse> actual = getRequest.call(mockTestScenario)

        then:
        actual.body.data == responseData
        actual.statusCode == HttpStatus.OK
        actual.headers.size() == 0

        where:
        URI_VARIABLES       || _
        ["Username": "ABC"] || _
        [:]                 || _
    }

    def "call - should handle exception"() {
        given:
        1 * mockRestTemplate.getForEntity(basePath, String.class, [:]) >> {
            throw new RestClientResponseException(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, null, null)
        }

        when:
        ResponseEntity<ApiResponse> actual = getRequest.call(mockTestScenario)

        then:
        actual.body.data == null
        actual.headers == [:]
        actual.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    }

}
