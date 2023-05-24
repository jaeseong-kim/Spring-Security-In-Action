package com.example.smallandsecurewebapllication6.service;

import com.example.smallandsecurewebapllication6.entity.Product;
import com.example.smallandsecurewebapllication6.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> findAll(){
    return productRepository.findAll();
  }
}
