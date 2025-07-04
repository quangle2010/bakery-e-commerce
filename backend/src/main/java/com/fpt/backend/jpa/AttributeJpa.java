package com.fpt.backend.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.backend.entity.Attribute;

public interface AttributeJpa extends JpaRepository<Attribute,Integer>{

}
