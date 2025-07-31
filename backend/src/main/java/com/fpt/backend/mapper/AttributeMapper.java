package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fpt.backend.entity.Attribute;

@Component
public class AttributeMapper {

    public Object toDTO(Attribute attribute) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attribute.getId());
        map.put("name", attribute.getName());
        return map;
    }
}
