package com.sayan.enotes.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DtoValidationException extends RuntimeException{

    private Map<String, Object> mapError;

    public DtoValidationException(Map<String, Object> mapError) {
        super("Validation Failed");
        this.mapError = mapError;
    }

    public Map<String, Object> getMapError() {
        return mapError;
    }
}
