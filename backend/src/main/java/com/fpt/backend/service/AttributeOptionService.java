package com.fpt.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.AttributeOption;
import com.fpt.backend.jpa.AttributeOptionJpa;

@Service
public class AttributeOptionService {

    @Autowired
    private AttributeOptionJpa attributeOptionJpa;

    public AttributeOption findById(int id) {
        return attributeOptionJpa.findById(id).orElse(null);
    }
}
