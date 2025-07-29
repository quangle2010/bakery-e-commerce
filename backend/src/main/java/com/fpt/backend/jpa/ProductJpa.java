package com.fpt.backend.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("""
            SELECT COUNT(p)
            FROM Product p
            WHERE (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    int totalItem(@Param("keyword") String keyword);
}