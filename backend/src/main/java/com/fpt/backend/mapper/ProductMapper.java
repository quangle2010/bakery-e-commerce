package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fpt.backend.entity.Product;
import com.fpt.backend.service.UserService;

@Component
public class ProductMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private AttributeOptionProductMapper attributeOptionProductMapper;

    public boolean isFavorite(String token, Integer productId) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        return userService.isFavorite(token, productId);
    }

    public Object toDTO(Product product) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", product.getId());
        map.put("name", product.getName());
        map.put("image", product.getImage());
        map.put("quantity", product.getQuantity());
        map.put("price", product.getPrice());
        map.put("description", product.getDescriptions());
        map.put("createAt", product.getCreateAt());
        map.put("weight", product.getWeight());
        map.put("category", attributeOptionProductMapper.toDTO(product.getAttributeOptionProducts()));
        return map;
    }

    //lấy thông tin đối tượng Product map về DTO
    public Object toDTODetail(Product product, String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", product.getId());
        map.put("name", product.getName());
        map.put("image", product.getImage());
        map.put("quantity", product.getQuantity());
        map.put("price", product.getPrice());
        map.put("description", product.getDescriptions());
        map.put("createAt", product.getCreateAt());
        map.put("weight", product.getWeight());
        map.put("isfavorite", isFavorite(token, product.getId()));
        map.put("category", attributeOptionProductMapper.toDTO(product.getAttributeOptionProducts()));
        return map;
    }
}
