-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `uid` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `nick_name` VARCHAR(45) NULL,
  `token` VARCHAR(45) NULL,
  `create_time` DATETIME NULL,
  `modify_time` DATETIME NULL,
  PRIMARY KEY (`uid`))
  ENGINE = InnoDB  DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `baby_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baby_info` ;

CREATE TABLE IF NOT EXISTS `baby_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nick_name` VARCHAR(45) NULL,
  `birthday` DATE NULL,
  `birth_weight` INT NULL,
  `province` INT NULL,
  `blood_type` VARCHAR(45) NULL,
  `create_time` DATETIME NULL,
  `modify_time` DATETIME NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `user_baby_rel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_baby_rel` ;

CREATE TABLE IF NOT EXISTS `user_baby_rel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `baby_id` INT NULL,
  `create_time` DATETIME NULL,
  `modify_time` DATETIME NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB DEFAULT CHARSET=utf8;
alter table `user_baby_rel` add unique index(user_id,baby_id);


-- -----------------------------------------------------
-- Table `baby_record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baby_record` ;

CREATE TABLE IF NOT EXISTS `baby_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `baby_id` INT NULL,
  `height` INT NULL,
  `weight` INT NULL,
  `create_time` DATETIME NULL,
  `modify_time` DATETIME NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB DEFAULT CHARSET=utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
