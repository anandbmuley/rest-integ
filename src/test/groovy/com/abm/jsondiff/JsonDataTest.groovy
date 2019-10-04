package com.abm.jsondiff

import spock.lang.Specification

import static com.abm.jsondiff.FileLoader.load

class JsonDataTest extends Specification {

    def "GetDataType"() {
        given:

        JsonData data = new JsonData(load("first.json"))

        when:
        String dataType = data.getDataType("insurances")

        then:
        dataType == ""
    }
}
