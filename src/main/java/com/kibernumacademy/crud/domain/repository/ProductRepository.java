package com.kibernumacademy.crud.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kibernumacademy.crud.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
