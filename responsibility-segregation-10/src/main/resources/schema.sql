USE SPRING;

DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS OTP;

CREATE TABLE USER (
  username VARCHAR(45) NULL,
  password TEXT NULL,
  PRIMARY KEY(username)
);

CREATE TABLE OTP(
  username VARCHAR(45) NOT NULL,
  code VARCHAR(45) NULL,
  PRIMARY KEY(username)
);
