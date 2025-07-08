package com.fpt.backend.util;

import java.util.Map;

public class CustomResponse {
    public static  final Map<String, Object> OBJECT(String key, Object value) {
        return Map.of(key, value);
    }   
    public static final Map<String, Object> ARRAYLIST_INT(Object array, int value) {
        return Map.of(
            "array", array,
            "value", value
        );
    }
}
