package com.example.responsibilitysegregation10.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Otp {

  @Id
  private String username;

  private String code;
}
