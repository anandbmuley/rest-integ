package com.abm.jsondiff

import org.json.JSONObject
import spock.lang.Specification

import static com.abm.jsondiff.FileLoader.load

class JsonDiffTest extends Specification {

    JsonDiff jsonDiff

    def setup() {
        jsonDiff = new JsonDiff()
    }

    def "diff - should find the"() {
        given:
        JSONObject first = load("first.json")
        JSONObject second = load("second.json")

        when:
        JSONObject diff = jsonDiff.diff(first, second)

        then:
        diff.keySet().size() == 2
        jsonDiff.report()
    }
}
