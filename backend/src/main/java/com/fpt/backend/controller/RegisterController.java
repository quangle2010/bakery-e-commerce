package com.fpt.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.bean.RegisterBean;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.AuthService;
import com.fpt.backend.util.ResponseEntityUtil;

import jakarta.validation.Valid;

@RestController
public class RegisterController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseData> register(@RequestBody @Valid RegisterBean registerBean, BindingResult result) {
        try {
            if (result.hasErrors()) {
                //Hiện lỗi bên bean
                return ResponseEntityUtil.BAD_REQUEST_BINDING_RESULT(result);
            }
            authService.register(registerBean);
            return ResponseEntityUtil.OK("Đăng ký thành công", null);
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }
}
