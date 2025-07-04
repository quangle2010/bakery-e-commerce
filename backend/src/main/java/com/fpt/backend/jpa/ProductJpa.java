package com.fpt.backend.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.backend.entity.Product;

public interface ProductJpa extends JpaRepository<Product, Integer> {

    @Query("""
            SELECT p
            FROM Product p
            WHERE (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    List<Product> findByKeyword(@Param("keyword") String keyword);

}