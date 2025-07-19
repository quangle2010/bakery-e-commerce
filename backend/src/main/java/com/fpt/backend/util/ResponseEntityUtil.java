package com.fpt.backend.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.fpt.backend.resp.ResponseData;

public class ResponseEntityUtil {

    public static ResponseEntity<ResponseData> OK(String message, Object data) {
        return ResponseEntity.ok(new ResponseData(true, message, data));
    }

    public static ResponseEntity<ResponseData> BAD_REQUEST(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(false, message, null));
    }

    public static ResponseEntity<ResponseData> UNAUTHORIZED(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(false, message, null));
    }

    

    public static ResponseEntity<ResponseData> BAD_REQUEST_BINDING_RESULT(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseData(false, "Validation failed", errors));
    }

  

}
