package com.fpt.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.Attribute;
import com.fpt.backend.jpa.AttributeJpa;
import com.fpt.backend.mapper.AttributeOptionMapper;

@Service
public class AttributeService {

    @Autowired
    private AttributeJpa attributeJpa;

    @Autowired
    private AttributeOptionMapper attributeOptionMapper;

    public Attribute findById(int id) {
        return attributeJpa.findById(id).orElse(null);
    }

    public List<Object> getAttributeOptions(int id) {
        Attribute attribute = findById(id);
        return attribute.getOptions().stream()
                .map(attributeOption -> attributeOptionMapper.toDTO(attributeOption))
                .collect(Collectors.toList());
    }

}
