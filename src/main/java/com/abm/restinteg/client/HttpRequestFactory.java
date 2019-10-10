package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;

public abstract class HttpRequestFactory {

    public static HttpRequest get(ApiRequest apiRequest) {
        HttpRequest httpRequest = null;
        switch (apiRequest.getHttpMethod()) {
            case GET:
                httpRequest = new GetRequest(apiRequest.getCompleteUrl());
                break;
            case POST:
                httpRequest = new PostRequest(apiRequest.getCompleteUrl());
                break;
            case PUT:
                httpRequest = new PutRequest(apiRequest.getCompleteUrl());
        }
        return httpRequest;
    }

}
