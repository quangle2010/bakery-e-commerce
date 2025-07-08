package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fpt.backend.entity.Product;

@Component
public class ProductMapper {
    
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
        map.put("isfavorite", false);
        map.put("category", "cake");
        return map;
    }
}
