package com.abm.jsondiff;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonDiff {

    private JsonDiffReport jsonDiffReport;
    private Map<String, Object> diffMap;

    public JsonDiff() {
        jsonDiffReport = new JsonDiffReport();
        diffMap = new LinkedHashMap<>();
    }

    public JSONObject diff(JSONObject left, JSONObject right) {
        Map<String, Object> leftMap = left.toMap();
        Map<String, Object> rightMap = right.toMap();

        leftMap.forEach((key, val) -> {
            if (rightMap.containsKey(key)) {
                jsonDiffReport.left(key, val);
            } else {
                diffMap.put(key, val);
                jsonDiffReport.leftDiff(key, val);
            }
        });

        rightMap.forEach((key, val) -> {
            if (leftMap.containsKey(key)) {
                jsonDiffReport.right(key, val);
            } else {
                diffMap.put(key, val);
                jsonDiffReport.rightDiff(key, val);
            }
        });

        return new JSONObject(diffMap);
    }

    public void report() {
        jsonDiffReport.asHtml();
    }

}
