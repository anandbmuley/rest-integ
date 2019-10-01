package com.abm.restinteg.models;

import com.abm.restinteg.models.config.ExpectedResponse;

import java.util.Map;

public class MapResponse extends ApiResponse {

    private Map<String, Object> data;

    public MapResponse(Map<String, Object> data) {
        super();
        this.data = data;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void validate(ExpectedResponse expectedResponse) throws Exception {
        if (data == null && expectedResponse.getBody() == null) {
            System.out.println("MATCHED");
        }
    }

}
