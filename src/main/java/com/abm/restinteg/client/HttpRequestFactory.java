package com.abm.restinteg.client;

import com.abm.restinteg.models.core.TestScenario;
import org.springframework.web.client.RestTemplate;

public abstract class HttpRequestFactory {

    public static HttpRequest get(TestScenario testScenario, RestTemplate restTemplate) {
        HttpRequest httpRequest = null;
        switch (testScenario.getHttpMethod()) {
            case GET:
                httpRequest = new GetRequest(testScenario.getCompleteUrl(), restTemplate);
                break;
            case POST:
                httpRequest = new PostRequest(testScenario.getCompleteUrl(), restTemplate);
                break;
            case PUT:
                httpRequest = new PutRequest(testScenario.getCompleteUrl(), restTemplate);
                break;
            case DELETE:
                httpRequest = new DeleteRequest(testScenario.getCompleteUrl(), restTemplate);
                break;
        }
        return httpRequest;
    }

}
