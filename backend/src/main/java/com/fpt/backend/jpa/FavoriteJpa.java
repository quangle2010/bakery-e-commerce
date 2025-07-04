package com.fpt.backend.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpt.backend.entity.Favorite;

public interface FavoriteJpa extends JpaRepository<Favorite, Integer> {

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.product.id = :productId")
    Optional<Favorite> findByUserIdAndProductId(Integer userId, Integer productId);
} 