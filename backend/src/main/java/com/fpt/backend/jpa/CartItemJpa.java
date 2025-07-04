package com.fpt.backend.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpt.backend.entity.CartItem;

public interface CartItemJpa extends JpaRepository<CartItem, Integer> {
    // Custom query methods can be defined here if needed

    @Query("SELECT c FROM CartItem c WHERE c.user.id = ?1 AND c.product.id = ?2")
    Optional<CartItem> findByUserIdAndProductId(int userId, int productId);
}
