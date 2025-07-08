package com.fpt.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.backend.bean.LoginBean;
import com.fpt.backend.entity.User;
import com.fpt.backend.security.JwtUtil;
import com.fpt.backend.util.Constant;

@Service
public class LoginService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(UserService userService, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String login(LoginBean loginBean) {
        User user = userService.findByEmailLogin(loginBean.getEmail());
        if (user == null || !passwordEncoder.matches(loginBean.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Tài khoản hoặc mật khẩu không đúng");
        }
        return jwtUtil.generateToken(user.getId(), user.getRole(), Constant.TYPEJWT_LOGIN);
    }
}
