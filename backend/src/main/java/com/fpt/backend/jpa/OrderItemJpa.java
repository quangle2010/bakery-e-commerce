package com.fpt.backend.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.backend.entity.OrderItem;

public interface OrderItemJpa extends JpaRepository<OrderItem,Integer>{

}
