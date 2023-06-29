package com.example.globalmethodsecurity17.service;

import com.example.globalmethodsecurity17.entity.Product;
import com.example.globalmethodsecurity17.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;


  // filterObject는 컬렉션에 저장되는 객체가 된다.
  @PreFilter("filterObject.owner == authentication.name")
  public List<Product> sellProducts(List<Product> products) {
    return products;
  }

  @PostFilter("filterObject.owner == authentication.name")
  public List<Product> findProducts() {
    List<Product> products = new ArrayList<>();

    return products;
  }

  public List<Product> findProductByNameContains(String text) {
    return productRepository.findProductByNameContains(text);
  }
}
