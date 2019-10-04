package com.abm.jsondiff.finders;

import com.abm.jsondiff.DiffType;
import org.json.JSONException;
import org.json.JSONObject;

public class ValueMismatchFinder extends Finder {

    public ValueMismatchFinder(JSONObject expected, JSONObject actual) {
        super(expected, actual);
    }

    @Override
    public void find() {
        expected.toMap().forEach((expKey, expVal) -> {
            try {
                Object actualVal = actual.get(expKey);
                if (!expVal.equals(actualVal)) {
                    addDiff(expKey, DiffType.VALUE_MISMATCH_101);
                }
            } catch (JSONException e) {
                // IGNORE
            }
        });
    }

}
