package com.fpt.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.UserService;
import com.fpt.backend.util.ResponseEntityUtil;

@RestController
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/auth")
    public ResponseEntity<ResponseData> getProfile(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntityUtil.OK("Lấy thông tin thành công", userService.getUserDTO(token));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }

    }
}
