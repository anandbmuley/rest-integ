package com.abm.jsondiff.finders;

import com.abm.jsondiff.DiffType;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public abstract class Finder {

    protected JSONObject expected;
    protected JSONObject actual;
    protected Set<FieldDiff> fieldDiffs;


    public Finder(JSONObject expected, JSONObject actual) {
        this.expected = expected;
        this.actual = actual;
        fieldDiffs = new HashSet<>();
    }

    public void addDiff(String fieldname, DiffType diff) {
        FieldDiff fieldDiff = fieldDiffs.stream()
                .filter($ -> $.getFieldname().equals(fieldname))
                .findAny().orElse(new FieldDiff(fieldname));
        fieldDiff.addDiff(diff);
        fieldDiffs.add(fieldDiff);
    }

    public abstract void find();

}
