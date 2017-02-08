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
  `open_id` VARCHAR(255) NULL,
  `access_token` VARCHAR(255) NULL,
  `access_token_invalid_time` DATETIME NULL,
  `refresh_token` VARCHAR(255) NULL,
  `nick_name` VARCHAR(255) NULL,
  `create_time` DATETIME NULL,
  `modify_time` DATETIME NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY (`open_id`)
)
  ENGINE = InnoDB  DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `baby_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baby_info` ;

CREATE TABLE IF NOT EXISTS `baby_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nick_name` VARCHAR(255) NULL,
  `head_img` VARCHAR(255) NULL,
  `birthday` DATE NULL,
  `birth_height` INT NULL,
  `birth_weight` INT NULL,
  `gender` INT NOT NULL DEFAULT 0 COMMENT '0 female 1 male',
  `province_id` INT NULL,
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

-- -----------------------------------------------------
-- Table `provinces`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `provinces` ;

CREATE TABLE IF NOT EXISTS `provinces` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB DEFAULT CHARSET=utf8;

insert into provinces values
  (1,'北京市'), (2,'广东省'), (3,'山东省'), (4,'江苏省'), (5,'河南省'),
  (6,'上海市'), (7,'河北省'), (8,'浙江省'), (9,'香港'), (10,'陕西省'),
  (11,'湖南省'), (12,'重庆市'), (13,'福建省'), (14,'天津市'), (15,'云南省'),
  (16,'四川省'), (17,'广西'), (18,'安徽省'), (19,'海南省'), (20,'江西省'),
  (21,'湖北省'), (22,'山西省'), (23,'辽宁省'), (24,'台湾省'), (25,'黑龙江'),
  (26,'内蒙古'), (27,'澳门'), (28,'贵州省'), (29,'甘肃省'), (30,'青海省'),
  (31,'新疆'), (32,'西藏'), (33,'吉林省'), (34,'宁夏');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
