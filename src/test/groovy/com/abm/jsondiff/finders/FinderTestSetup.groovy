package com.abm.jsondiff.finders

import org.json.JSONObject
import spock.lang.Specification

import static com.abm.jsondiff.FileLoader.load

class FinderTestSetup extends Specification {

    JSONObject expected
    JSONObject actual

    def setup() {
        expected = load("first.json")
        actual = load("second.json")
    }

}
