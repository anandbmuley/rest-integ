package com.abm.jsondiff

import org.json.JSONObject

class FileLoader {

    static JSONObject load(String filename) {
        new JSONObject(new File("src/test/resources/"+filename).text)
    }

}
