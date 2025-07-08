package com.fpt.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.bean.ProfileBean;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.UserService;
import com.fpt.backend.util.ResponseEntityUtil;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PutMapping("auth")
    public ResponseEntity<ResponseData> postProfile(@RequestHeader("Authorization") String token,
            @Valid @RequestBody ProfileBean profileBean, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntityUtil.BAD_REQUEST_BINDING_RESULT(result);
            }
            userService.updateProfile(token, profileBean);
            return ResponseEntityUtil.OK("Cập nhật thông tin thành công", null);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }

}
