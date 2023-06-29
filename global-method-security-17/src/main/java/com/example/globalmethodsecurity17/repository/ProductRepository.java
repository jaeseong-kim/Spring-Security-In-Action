package com.example.globalmethodsecurity17.repository;

import com.example.globalmethodsecurity17.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Integer> {

  @Query("SELECT p FROM Product p WHERE p.name LIKE %: text% AND P.owner =?#{authentication.name}")
  List<Product> findProductByNameContains(String text);
}
