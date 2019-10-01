package com.abm.restinteg.client;

import com.abm.restinteg.models.ApiRequest;
import com.abm.restinteg.models.ApiResponse;
import com.abm.restinteg.models.ListResponse;
import com.abm.restinteg.models.MapResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class GetRequest extends HttpRequest {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    public ResponseEntity<ApiResponse> call(ApiRequest apiRequest) {
        if (apiRequest.isResponseListData()) {
            ResponseEntity<List> entity = restTemplate.getForEntity(url, List.class);
            return ResponseEntity
                    .status(entity.getStatusCode())
                    .headers(entity.getHeaders())
                    .body(new ListResponse(entity.getBody()));
        } else {
            ResponseEntity<Map> entity = restTemplate.getForEntity(url, Map.class);
            return ResponseEntity
                    .status(entity.getStatusCode())
                    .headers(entity.getHeaders())
                    .body(new MapResponse(entity.getBody()));
        }
    }

}
