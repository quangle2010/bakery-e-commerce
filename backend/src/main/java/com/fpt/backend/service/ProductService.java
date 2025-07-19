package com.fpt.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.backend.entity.Product;
import com.fpt.backend.jpa.ProductJpa;
import com.fpt.backend.mapper.ProductMapper;


@Service
public class ProductService {

    @Autowired
    private ProductJpa productJpa;

    @Autowired
    private ProductMapper productMapper;

    public List<Object> getProductSearch(String keyword, Pageable pageable) {
        Page<Product> products;
        if (keyword == null || keyword.isEmpty()) {
            products = productJpa.findAll(pageable);
        } else {
            products = productJpa.findByKeyword(keyword, pageable);
        }
        return  products.stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public int totalItem(String keyword){
       return productJpa.totalItem(keyword);
    }

public Product getProductById(Integer id) {
        return productJpa.findById(id).orElse(null);
    }

    public Object getProductDTO(int id){
        Product product=productJpa.findById(id).orElseGet(null);
        return productMapper.toDTO(product);
    }

}
