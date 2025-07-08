package com.fpt.backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.backend.bean.ChangePasswordBean;
import com.fpt.backend.bean.RegisterBean;
import com.fpt.backend.entity.User;
import com.fpt.backend.util.FormatCustomerInfo;

@Service
public class AuthService {

    private final APIEmailService apiEmailService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(APIEmailService apiEmailService, UserService userService,
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

    public User changePassword(String token, ChangePasswordBean changePasswordBean) {
        User user = userService.getUserIsLogin(token);
        if (!bCryptPasswordEncoder.matches(changePasswordBean.getPasswordOld(), user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu cũ không đúng");
        }
        if (!changePasswordBean.isPasswordMatch()) {
            throw new IllegalArgumentException("Mật khẩu mới và xác nhận mật khẩu mới không khớp");
        }
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordBean.getPasswordNew()));
        return userService.save(user);
    }

}
