CREATE TABLE `aloha`.`board` (
  `idboard` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `content` TEXT NULL,
  `writer` VARCHAR(45) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT now(),
  `upd_date` TIMESTAMP NOT NULL DEFAULT now(),
  PRIMARY KEY (`idboard`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '게시판';
