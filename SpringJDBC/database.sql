drop database IF EXISTS `demo-spring-jdbc-transaction`;
create schema `demo-spring-jdbc-transaction`;
use `demo-spring-jdbc-transaction`;
CREATE TABLE `demo-spring-jdbc-transaction`.`user_info` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `amount` decimal(20) DEFAULT 0,
  PRIMARY KEY (`id`));
  
  select * from `demo-spring-jdbc-transaction`.`user_info`;
  
SELECT amount FROM `demo-spring-jdbc-transaction`.`user_info` WHERE id=1;