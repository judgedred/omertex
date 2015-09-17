CREATE USER admin@localhost identified BY 'admin';
GRANT usage ON *.* TO admin@localhost identified BY 'admin';
CREATE DATABASE IF NOT EXISTS support;
GRANT ALL privileges ON support.* TO admin@localhost;
USE support;
CREATE TABLE IF NOT EXISTS `support`.`Topic` (
  `topic_id` INT NOT NULL AUTO_INCREMENT,
  `topic_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`topic_id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `support`.`Inquiry` (
  `inquiry_id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  `create_date` TIMESTAMP(0) NOT NULL DEFAULT Now(),
  `customer_name` VARCHAR(45) NOT NULL,
  `topic_id` INT NOT NULL,
  PRIMARY KEY (`inquiry_id`),
  UNIQUE INDEX `inquiry_id_UNIQUE` (`inquiry_id` ASC),
  INDEX `fk_Inquiry_Topic_idx` (`topic_id` ASC),
  CONSTRAINT `fk_Inquiry_Topic`
    FOREIGN KEY (`topic_id`)
    REFERENCES `support`.`Topic` (`topic_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `support`.`AttributeOfInquery` (
  `attributeOfInquery_id` INT NOT NULL,
  `attribute_name` VARCHAR(45) NOT NULL,
  `attribute_value` VARCHAR(45) NOT NULL,
  `inquiry_id` INT NOT NULL,
  PRIMARY KEY (`attributeOfInquery_id`),
  INDEX `fk_AttributeOfInquery_Inquiry1_idx` (`inquiry_id` ASC),
  CONSTRAINT `fk_AttributeOfInquery_Inquiry1`
    FOREIGN KEY (`inquiry_id`)
    REFERENCES `support`.`Inquiry` (`inquiry_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;