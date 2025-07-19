package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fpt.backend.entity.CartItem;

@Component
public class CartItemMapper {
    @Autowired
    private ProductMapper productMapper;
    public Object toDTO(CartItem item) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", item.getId());
        map.put("quantity", item.getQuantity());
        map.put("product", productMapper.toDTO(item.getProduct()));
        return map;
    }
}
