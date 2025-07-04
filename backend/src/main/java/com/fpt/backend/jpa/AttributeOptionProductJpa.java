package com.fpt.backend.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.backend.entity.AttributeOptionProduct;
import com.fpt.backend.entity.Product;

public interface AttributeOptionProductJpa extends JpaRepository<AttributeOptionProduct, Integer> {
    List<AttributeOptionProduct> findByProduct(Product product);
}
