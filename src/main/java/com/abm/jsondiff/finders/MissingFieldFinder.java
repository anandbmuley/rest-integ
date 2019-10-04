package com.abm.jsondiff.finders;

import com.abm.jsondiff.DiffType;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MissingFieldFinder extends Finder {

    public MissingFieldFinder(JSONObject left, JSONObject right) {
        super(left, right);
    }

    @Override
    public void find() {
        identifyMissingField(expected.toMap(), actual, DiffType.MISSING_FIELD_101);
        identifyMissingField(actual.toMap(), expected, DiffType.MISSING_FIELD_102);
    }

    private void identifyMissingField(Map<String, Object> dataMap, JSONObject lookup, DiffType diffType) {
        dataMap.forEach((String key, Object val) -> {
            try {
                lookup.get(key);
            } catch (JSONException e) {
                addDiff(key, diffType);
            }
        });
    }

}
