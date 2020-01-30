package com.abm.restinteg.client;

import com.abm.restinteg.models.core.TestScenario;

public abstract class HttpRequestFactory {

    public static HttpRequest get(TestScenario testScenario) {
        HttpRequest httpRequest = null;
        switch (testScenario.getHttpMethod()) {
            case GET:
                httpRequest = new GetRequest(testScenario.getCompleteUrl());
                break;
            case POST:
                httpRequest = new PostRequest(testScenario.getCompleteUrl());
                break;
            case PUT:
                httpRequest = new PutRequest(testScenario.getCompleteUrl());
                break;
            case DELETE:
                httpRequest = new DeleteRequest(testScenario.getCompleteUrl());
                break;
        }
        return httpRequest;
    }

}
