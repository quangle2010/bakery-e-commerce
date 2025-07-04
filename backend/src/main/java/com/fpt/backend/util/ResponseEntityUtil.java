package com.fpt.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fpt.backend.resp.ResponseData;

public class ResponseEntityUtil {

    public static ResponseEntity<ResponseData> OK(String message, Object data) {
        return ResponseEntity.ok(new ResponseData(true, message, data));
    }

    public static ResponseEntity<ResponseData> BAD_REQUEST(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(false, message, null));
    }

}
