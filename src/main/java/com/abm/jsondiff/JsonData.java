package com.abm.jsondiff;

import org.json.JSONObject;

public class JsonData {

    private JSONObject jsonObject;

    public JsonData(String data) {
        this.jsonObject = new JSONObject(data);
    }

    public JsonData(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getDataType(String fieldname) {
        return jsonObject.get(fieldname).getClass().getSimpleName();
    }

}
