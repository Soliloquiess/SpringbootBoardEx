-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: aloha
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `content` text COLLATE utf8mb4_bin,
  `writer` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_path` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `group_no` int DEFAULT '0',
  `depth_no` int DEFAULT '0',
  `seq_no` int DEFAULT '0',
  `view` int DEFAULT '0',
  PRIMARY KEY (`board_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1507 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='게시판';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1495,'원본글1','124123','124','2021-07-16 12:08:27','2021-07-22 08:50:02',NULL,1495,0,0,0),(1496,'수정2','REST 테스트','이명박2','2021-07-16 12:08:36','2021-07-26 08:26:22',NULL,1495,1,1,0),(1497,'답글1의 답글','ㅁㄴㅇㄱ','4124','2021-07-16 12:08:45','2021-07-16 12:08:45',NULL,1495,2,1,0),(1498,'원본글2','ㅁㄴㄱ','1','2021-07-16 12:08:55','2021-07-16 12:08:55',NULL,1498,0,0,0),(1499,'답글2','ㅂㅈㄱ1241241241212','ㄻㄴㅇ','2021-07-16 12:09:22','2021-07-28 05:26:45',NULL,1498,1,1,0),(1504,'12','12312','123','2021-07-28 06:17:06','2021-07-28 06:17:06',NULL,0,0,0,0),(1505,'432','12312','12','2021-07-28 06:22:34','2021-07-28 06:22:34',NULL,0,0,0,0),(1506,'123','12312','123','2021-07-28 06:55:46','2021-07-28 06:55:46',NULL,0,0,0,0);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_attach`
--

DROP TABLE IF EXISTS `board_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_attach` (
  `full_name` varchar(150) COLLATE utf8mb4_bin NOT NULL,
  `board_no` int NOT NULL,
  `reg_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `file_name` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `file_no` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`file_no`),
  KEY `FK_BOARD_NO_idx` (`board_no`),
  KEY `FK_BOARD_NO_idx2` (`board_no`),
  CONSTRAINT `board_no` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_attach`
--

LOCK TABLES `board_attach` WRITE;
/*!40000 ALTER TABLE `board_attach` DISABLE KEYS */;
INSERT INTO `board_attach` VALUES ('C:/ALOHA CAMPUS/workspace/SPRING_CYH/board-file/upload/609a925b-303a-493b-8ef6-ed27dc8f8ade_impo.png',1504,'2021-07-28 06:17:06','impo.png',16),('C:/ssafyyyy/daaff7ad-7860-414d-89f0-c819012c7c2f_impo.png',1505,'2021-07-28 06:22:34','impo.png',17),('C:/ssafyyyy/6ddd711a-418e-4f8b-aed4-10b7cdd19911_09170eb436401a39.gif',1506,'2021-07-28 06:55:46','09170eb436401a39.gif',18);
/*!40000 ALTER TABLE `board_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_reply`
--

DROP TABLE IF EXISTS `board_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_reply` (
  `reply_no` int NOT NULL AUTO_INCREMENT,
  `writer` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `content` text COLLATE utf8mb4_bin NOT NULL,
  `board_no` int NOT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reply_no`),
  KEY `board_no_fk_idx` (`board_no`),
  CONSTRAINT `board_no_fk` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_reply`
--

LOCK TABLES `board_reply` WRITE;
/*!40000 ALTER TABLE `board_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `user_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `user_pw` varchar(100) NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `reg_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` char(1) DEFAULT '1',
  `email` varchar(100) DEFAULT NULL,
  `cell` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'abcd','$2a$10$4/eXF2Jdn/gx2TitbbZRmu2mMDb/R0JfU3xlsWfCJsIHxeZSRmsp.','abd','2021-07-19 08:26:47','2021-07-19 08:26:47','1',NULL,NULL),(2,'abcde','$2a$10$TXHka0d0wKiV4xw4O5hXmeOkTiyjmn/CQ4T697SNj9a2Z8HHUDi.O','abcde','2021-07-20 01:30:24','2021-07-20 01:30:24','1',NULL,NULL),(3,'zxcv','$2a$10$eOB952PDCVm7UWFw/6Cebu6sR14p29YMdc4yMxRBQSlDE/WLVdrk2','zxcv','2021-07-20 01:34:34','2021-07-20 01:34:34','1',NULL,NULL),(4,'asdf','$2a$10$eHLIkH.m5YHdWWKpVIXq8uibwGT5WcQWTiA/PCUb4vURpMeN3lARi','asdf','2021-07-20 01:38:09','2021-07-20 01:38:09','1','ssibal@naver.com',NULL),(5,'wert','$2a$10$hwkPXrDX4FglWeAArEq6aeXnUoLSrhDwYH1XnuXvjk1orrV127fHC','wert','2021-07-20 02:04:38','2021-07-20 02:04:38','1','wert@naver.com',NULL),(6,'wert','$2a$10$HvJJ7wWt8RsN2TtsYUm7G.c5PTddT4pfdVobGd/iG4Rac0HHVooGu','wert','2021-07-20 02:08:21','2021-07-20 02:08:21','1','wert@naver.com',NULL),(7,'wert','$2a$10$Rs9ynYokcYxJH6wxCYHZtuBCUEKD4auUmZPQaNltyl4G5MXziQf/S','wert','2021-07-20 02:11:01','2021-07-20 02:11:01','1','wert',NULL),(8,'test','$2a$10$Ozg4B/SH7RfBALcqMt/XYuo2GnnTnbdxMVg2BXJSJ4nPRUjem8du2','test','2021-07-21 11:30:29','2021-07-21 11:30:29','1',NULL,NULL),(9,'wert','$2a$10$ZVpEfvhRYuoAvI64ROX9yuxrC5YAgbPco5AGRNgU0XdUMqMhXv.YW','wert','2021-07-23 10:01:07','2021-07-23 10:01:07','1',NULL,NULL),(10,'wert','$2a$10$cYFNN.GDAYk2dhKTh3BDouE5A4Lhc/o9kZTIiUhLniHvbdtGABUyK','wert','2021-07-23 10:01:24','2021-07-23 10:01:24','1',NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-28 16:47:12
