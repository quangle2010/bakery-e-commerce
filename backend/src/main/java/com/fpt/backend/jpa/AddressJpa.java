package com.fpt.backend.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpt.backend.entity.Address;

public interface AddressJpa extends JpaRepository<Address, Integer> {

    List<Address> findByUserId(Integer userId);

    Optional<Address> findByUserIdAndIsDefaultTrue(Integer userId);

    @Query("SELECT a FROM Address a WHERE a.id = :id AND a.user.id = :userId")
    Optional<Address> findByIdAndUserId(Integer id, Integer userId);
}
