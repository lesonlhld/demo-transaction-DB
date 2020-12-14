use `demo-spring-jdbc-transaction`;
CREATE TABLE `demo-spring-jdbc-transaction`.`user_info` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));
  
  select * from `demo-spring-jdbc-transaction`.`user_info`;