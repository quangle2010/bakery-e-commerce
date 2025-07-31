package com.fpt.backend.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fpt.backend.entity.AttributeOption;

@Component
public class AttributeOptionMapper {

    public Object toDTO(AttributeOption attributeOption) {
      Map<String, Object> map = new HashMap<>();
        map.put("id", attributeOption.getId());
        map.put("name", attributeOption.getName());
        return map;
    }
}
