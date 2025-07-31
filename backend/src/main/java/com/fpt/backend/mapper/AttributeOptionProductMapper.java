package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fpt.backend.entity.AttributeOptionProduct;

@Component
public class AttributeOptionProductMapper {


 public Object toDTO(List<AttributeOptionProduct> attributeOptionProducts) {
    if (attributeOptionProducts == null || attributeOptionProducts.isEmpty()) {
        return null;
    }
    return attributeOptionProducts.stream()
    .sorted((a, b) -> Integer.compare(
        b.getAttributeOption().getAttribute().getId(),
        a.getAttributeOption().getAttribute().getId()
    ))
    .map(aop -> {
        Map<String, Object> map = new HashMap<>();
        map.put("id", aop.getAttributeOption().getId());
        map.put("name", aop.getAttributeOption().getName());
        return map;
    })
    .collect(Collectors.toList());

}

}
