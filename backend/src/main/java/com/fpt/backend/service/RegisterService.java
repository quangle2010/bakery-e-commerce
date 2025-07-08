package com.fpt.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.backend.bean.RegisterBean;
import com.fpt.backend.entity.User;
import com.fpt.backend.util.FormatCustomerInfo;

@Service
public class RegisterService {

    private final APIEmailService apiEmailService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterService(APIEmailService apiEmailService, UserService userService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.apiEmailService = apiEmailService;
        this.userService = userService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public User register(RegisterBean registerBean) {
        User user = new User();
        user.setFullName(FormatCustomerInfo.customFullName(registerBean.getFullName()));
        user.setEmail(registerBean.getEmail());
        if (!apiEmailService.isEmailVerified(registerBean.getEmail())) {
            throw new IllegalArgumentException("Email Không tồn tại");
        }
        if (userService.checkEmail(registerBean.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }
        if (!registerBean.isPasswordMatch()) {
            throw new IllegalArgumentException("Mật khẩu và xác nhận mật khẩu không khớp");
        }
        user.setPassword(bCryptPasswordEncoder.encode(registerBean.getPassword()));
        return userService.save(user);
    }
}
