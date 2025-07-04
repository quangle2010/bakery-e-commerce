package com.fpt.backend.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.backend.entity.Address;

public interface AddressJpa extends JpaRepository<Address, Integer> {
    
} 
