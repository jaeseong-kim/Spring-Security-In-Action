package com.example.globalmethodsecurity17.controller;

import com.example.globalmethodsecurity17.entity.Product;
import com.example.globalmethodsecurity17.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping("/sell")
  public List<Product> sellProduct() {
    List<Product> products = new ArrayList<>();



    return productService.sellProducts(products);
  }

  @GetMapping("/find")
  public List<Product> findProducts() {
    return productService.findProducts();
  }

  @GetMapping("/products/{text}")
  public List<Product> findProductsContaining(@PathVariable String text){
    return productService.findProductByNameContains(text);
  }
}
