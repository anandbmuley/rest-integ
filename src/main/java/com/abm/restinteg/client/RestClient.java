package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiResponse;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class RestClient {

    private RestTemplate restTemplate;

    public RestClient() {
        restTemplate = new RestTemplate();
    }

    public ApiResponse call(String url) {
        List<Map<String, Object>> data = restTemplate.getForObject(url, List.class);
        return new ApiResponse(data);
    }

    public static void main(String[] args) {
        RestClient restClient = new RestClient();
        System.out.println(restClient.call("http://localhost:8080/home-automation/api/bills"));
    }

}
