package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fpt.backend.entity.User;
import com.fpt.backend.util.Constant;

@Component
public class UserMapper {

    public Map<String, Object> toDTO(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("fullName", user.getFullName());
        map.put("email", user.getEmail());
        map.put("phone", user.getPhone());
        map.put("address", user.getAddress());
        map.put("role", user.getRole() == 0 ? Constant.ROLE_USER_NAME : Constant.ROLE_ADMIN_NAME);
        map.put("cart", user.getCartItems().isEmpty() ? 0 : user.getCartItems().size());
        return map;
    }
}
