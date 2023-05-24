package com.example.smallandsecurewebapllication6.entity;

import com.example.smallandsecurewebapllication6.type.Currency;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private double price;

  @Enumerated(EnumType.STRING)
  private Currency currency;
}
