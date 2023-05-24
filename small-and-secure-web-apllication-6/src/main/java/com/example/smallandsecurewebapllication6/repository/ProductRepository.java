package com.example.smallandsecurewebapllication6.repository;

import com.example.smallandsecurewebapllication6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {


}
