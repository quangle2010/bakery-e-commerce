package com.fpt.backend.service;

import org.springframework.stereotype.Service;

import com.fpt.backend.bean.RegisterBean;
// import com.fpt.backend.bean.ChangePasswordBean;
// import com.fpt.backend.bean.LoginBean;
// import com.fpt.backend.bean.ProfileBean;
// import com.fpt.backend.bean.RegisterBean;
import com.fpt.backend.entity.User;
import com.fpt.backend.jpa.UserJpa;
import com.fpt.backend.mapper.UserMapper;
import com.fpt.backend.security.JwtUtil;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserJpa userJpa;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    public User findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        return userJpa.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với email: " + email));
    }

    public User findUserById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID không được để trống");
        }
        return userJpa.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với ID: " + id));
    }

    public User checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        return userJpa.findByEmail(email).orElse(null);

    }

    public User findByEmailLogin(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        return userJpa.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Tài khoản hoặc mật khẩu không đúng"));
    }

    public User save(User user) {
        return userJpa.save(user);
    }

    public User findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID không được để trống");
        }
        return userJpa.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với ID: " + id));
    }

    public User getUserIsLogin(String token) {
        String jwt=token.startsWith("Bearer ")
                ? token.substring(7)
                : token;
        String userId = jwtUtil.extractUserId(jwt);
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("ID không được null");
        }
        Integer id = Integer.parseInt(userId);
        return findById(id);
    }

    public Map<String,Object> getUserDTO(String token){
        return userMapper.toDTO(getUserIsLogin(token));
    }


  public User register(RegisterBean registerBean) {
        User user = new User();
        user.setFullName(registerBean.getFullName());
        user.setEmail(registerBean.getEmail());
        user.setPassword(registerBean.getPassword());
        if (!registerBean.isPasswordMatch()) {
            throw new IllegalArgumentException("Mật khẩu và xác nhận mật khẩu không khớp");
        }
        if (checkEmail(registerBean.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }
        return save(user);
    }

}
