package com.fpt.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.AttributeOptionProduct;
import com.fpt.backend.entity.Product;
import com.fpt.backend.jpa.AttributeOptionProductJpa;

@Service
public class AttributeOptionProductService {
    @Autowired
    private AttributeOptionProductJpa attributeOptionProductJpa;

    public AttributeOptionProduct save(AttributeOptionProduct attributeOptionProduct) {
        return attributeOptionProductJpa.save(attributeOptionProduct);
    }

    public void deleteByProduct(Product product) {
        List<AttributeOptionProduct> list = attributeOptionProductJpa.findByProduct(product);
        attributeOptionProductJpa.deleteAll(list);
    }
}
