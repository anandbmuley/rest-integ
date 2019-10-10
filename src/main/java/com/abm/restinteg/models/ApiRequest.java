package com.abm.restinteg.models;

import java.util.Map;

public class ApiRequest {

    private String url;
    private final String baseUrl;
    private String body;
    private HttpMethod httpMethod;
    private boolean responseListData;
    private Map<String,String> pathVariables;

    public ApiRequest(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        body = null;
        responseListData = false;
    }

    public void setPathVariables(Map<String, String> pathVariables) {
        this.pathVariables = pathVariables;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setResponseListData(boolean responseListData) {
        this.responseListData = responseListData;
    }

    public boolean isResponseListData() {
        return responseListData;
    }

    public Map<String, String> getPathVariables() {
        return pathVariables;
    }

    public String getCompleteUrl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(baseUrl).append(url);
        return stringBuilder.toString();
    }

    public String getBody() {
        return body;
    }
}
