package com.abm.restinteg.validators;

import com.abm.restinteg.models.HttpMethod;

public abstract class ResponseValidatorFactory {

    public static ResponseValidator get(HttpMethod httpMethod) {
        ResponseValidator responseValidator = null;
        switch (httpMethod) {
            case GET:
                responseValidator = new HttpGetValidator();
                break;
            case POST:
                responseValidator = new HttpPostValidator();
                break;
        }
        return responseValidator;
    }

}
