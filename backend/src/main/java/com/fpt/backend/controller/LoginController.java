package com.fpt.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.backend.bean.LoginBean;
import com.fpt.backend.resp.ResponseData;
import com.fpt.backend.service.LoginService;
import com.fpt.backend.util.CustomResponse;
import com.fpt.backend.util.ResponseEntityUtil;

import jakarta.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody @Valid LoginBean loginBean, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseEntityUtil.BAD_REQUEST_BINDING_RESULT(result);
            }
            String token = loginService.login(loginBean);
            return ResponseEntityUtil.OK("Đăng nhập thành công", CustomResponse.OBJECT("token", token));
        } catch (Exception e) {
            return ResponseEntityUtil.BAD_REQUEST(e.getMessage());
        }
    }
}
