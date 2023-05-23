DROP TABLE IF EXISTS spring.users;
DROP TABLE IF EXISTS spring.authorities;

CREATE TABLE spring.users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  enabled INT NOT NULL,
  PRIMARY KEY(id)
 );

CREATE TABLE spring.authorities (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  authority VARCHAR(45) NOT NULL,
  PRIMARY KEY(id)
);