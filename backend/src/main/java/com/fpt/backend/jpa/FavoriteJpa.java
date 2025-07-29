package com.fpt.backend.jpa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.backend.entity.Favorite;

public interface FavoriteJpa extends JpaRepository<Favorite, Integer> {

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.product.id = :productId")
    Optional<Favorite> findByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId")
    Page<Favorite> findByUserId(@Param("userId") Integer userId, Pageable pageable);
    
    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.user.id = :userId")
    int countByUserId(@Param("userId") Integer userId);
} 