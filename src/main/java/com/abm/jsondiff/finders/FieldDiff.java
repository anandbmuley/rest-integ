package com.abm.jsondiff.finders;

import com.abm.jsondiff.DiffType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldDiff implements Comparable<FieldDiff> {

    private String fieldname;
    private List<DiffType> differences;

    public FieldDiff(String fieldname) {
        this.fieldname = fieldname;
        differences = new ArrayList<>();
    }

    public String getFieldname() {
        return fieldname;
    }

    public void addDiff(DiffType diff) {
        differences.add(diff);
    }

    public List<DiffType> getDifferences() {
        return Collections.unmodifiableList(differences);
    }

    @Override
    public int compareTo(FieldDiff other) {
        return fieldname.compareTo(other.fieldname);
    }

    @Override
    public String toString() {
        return "FieldDiff{" +
                "fieldname='" + fieldname + '\'' +
                '}';
    }
}
