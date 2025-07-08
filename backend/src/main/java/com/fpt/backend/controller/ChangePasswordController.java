package com.fpt.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.bean.ChangePasswordBean;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.AuthService;
import com.fpt.backend.util.ResponseEntityUtil;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@RestController
public class ChangePasswordController {
    @Autowired
    private AuthService authService;

    @PutMapping("/auth/change-password")
    public ResponseEntity<ResponseData> changePassword(@RequestHeader("Authorization") String token,
         @Valid  @RequestBody ChangePasswordBean changePasswordBean, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntityUtil.BAD_REQUEST_BINDING_RESULT(result);
            }
            authService.changePassword(token, changePasswordBean);
            return ResponseEntityUtil.OK("Cập nhật thành công, Vui lòng đăng nhập lại", null);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }
}
