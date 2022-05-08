CREATE DATABASE  IF NOT EXISTS `aloha` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aloha`;
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
) ENGINE=InnoDB AUTO_INCREMENT=1551 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='게시판';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1496,'수정2','REST 테스트','이명박2','2021-07-16 12:08:36','2021-07-26 08:26:22',NULL,1495,1,1,0),(1497,'답글1의 답글','ㅁㄴㅇㄱ','4124','2021-07-16 12:08:45','2021-07-16 12:08:45',NULL,1495,2,1,0),(1498,'원본글2','ㅁㄴㄱ','1','2021-07-16 12:08:55','2021-07-16 12:08:55',NULL,1498,0,0,0),(1499,'답글2','ㅂㅈㄱ1241241241212','ㄻㄴㅇ','2021-07-16 12:09:22','2021-07-28 05:26:45',NULL,1498,1,1,0),(1504,'12','12312','123','2021-07-28 06:17:06','2021-07-28 06:17:06',NULL,0,0,0,0),(1505,'432','12312','12','2021-07-28 06:22:34','2021-07-28 06:22:34',NULL,0,0,0,0),(1506,'123','12312','123','2021-07-28 06:55:46','2021-07-28 06:55:46',NULL,0,0,0,0),(1507,'23423','asdfasd','123','2021-07-28 08:46:34','2021-08-02 11:20:26',NULL,1507,0,0,0),(1509,'12312','124124','12412','2021-08-02 13:12:09','2021-08-02 13:12:09',NULL,1509,0,0,0),(1510,'aaa','aaa','aaa','2021-08-02 13:12:13','2021-08-02 13:12:13',NULL,1510,0,0,0),(1511,'fdsf','srewr','sdf','2021-08-02 13:12:21','2021-08-02 13:12:21',NULL,1511,0,0,0),(1512,'gdfg','sdfg','sdfg','2021-08-02 13:12:26','2021-08-02 13:12:26',NULL,1512,0,0,0),(1513,'hsdfh','sfd','hdf','2021-08-02 13:12:30','2021-08-02 13:12:30',NULL,1513,0,0,0),(1514,'hsdfhasd','sfd12','hdf12','2021-08-02 13:12:34','2021-08-02 13:12:34',NULL,1514,0,0,0),(1515,'hsdfhasd5','sfd125','hdf12','2021-08-02 13:12:37','2021-08-02 13:12:37',NULL,1515,0,0,0),(1516,'글 읽기 테스트','123','123','2021-08-04 12:47:57','2021-08-09 11:10:49',NULL,1516,0,0,0),(1518,'1234',NULL,'1234','2021-08-09 11:10:59','2021-08-11 12:57:23',NULL,1518,0,0,0),(1519,'1518',NULL,'1234','2021-08-10 06:50:52','2021-08-10 06:50:52',NULL,1519,0,0,0),(1520,'1518','1245','1234','2021-08-10 06:51:02','2021-08-10 06:51:02',NULL,1520,0,0,0),(1521,'1518','1245','1234','2021-08-10 06:51:03','2021-08-10 06:51:03',NULL,1521,0,0,0),(1522,'1518','1245','1234','2021-08-10 06:51:04','2021-08-10 06:51:04',NULL,1522,0,0,0),(1523,'1518','1245','1234','2021-08-10 06:51:04','2021-08-10 06:51:04',NULL,1523,0,0,0),(1524,'1518','1245','1234','2021-08-10 06:51:05','2021-08-10 06:51:05',NULL,1524,0,0,0),(1525,'1518','1245','1234','2021-08-10 06:51:05','2021-08-10 06:51:05',NULL,1525,0,0,0),(1527,'asdf','asdf','asdf','2021-08-10 06:55:21','2021-08-10 06:56:40',NULL,1527,0,0,0),(1528,'1234','','1234','2021-08-10 07:01:19','2021-08-10 07:01:19',NULL,1528,0,0,0),(1529,'1234','','1234','2021-08-10 07:01:30','2021-08-10 07:01:30',NULL,1529,0,0,0),(1530,'1234','','1234','2021-08-10 07:02:00','2021-08-10 07:02:00',NULL,1530,0,0,0),(1531,'1234','12412412','1234','2021-08-10 07:03:13','2021-08-10 07:03:13',NULL,1531,0,0,0),(1532,'1234','12412412','1234','2021-08-10 07:03:17','2021-08-10 07:03:17',NULL,1532,0,0,0),(1533,'1234','4','1234','2021-08-10 07:03:50','2021-08-10 07:03:50',NULL,1533,0,0,0),(1534,'1234','','1234','2021-08-10 07:28:22','2021-08-10 07:28:22',NULL,1534,0,0,0),(1535,'1234','','1234','2021-08-10 07:28:25','2021-08-10 07:28:25',NULL,1535,0,0,0),(1536,'123','123','123','2021-08-11 11:19:17','2021-08-11 11:19:17',NULL,1534,1,1,0),(1537,'12444','4444','4444','2021-08-11 11:19:52','2021-08-11 11:19:52',NULL,1534,2,1,0),(1538,'253','235','235','2021-08-11 11:20:14','2021-08-11 11:20:14',NULL,1534,3,1,0),(1539,'123','','412','2021-08-16 07:28:47','2021-08-16 07:28:47',NULL,1539,0,0,0),(1540,'123412412','124','123412412','2021-08-18 18:19:16','2021-08-18 18:19:16',NULL,1540,0,0,0),(1541,'123412412','124','123412412','2021-08-18 18:19:28','2021-08-18 18:19:28',NULL,1541,0,0,0),(1542,'123412412','124','123412412','2021-08-18 18:19:38','2021-08-18 18:19:38',NULL,1542,0,0,0),(1543,'a','a','a','2021-08-23 12:12:47','2021-08-23 12:12:47',NULL,1543,0,0,0),(1544,'111','111','zxcv','2021-08-23 12:34:13','2021-08-23 12:34:13',NULL,1544,0,0,0),(1545,'12','12','zxcv','2021-08-23 12:36:36','2021-08-23 12:36:36',NULL,1545,0,0,0),(1546,'aa','aa','zxcv','2021-08-23 12:36:52','2021-08-23 12:36:52',NULL,1546,0,0,0),(1547,'1','1','zxcv','2021-08-23 12:39:53','2021-08-23 12:39:53',NULL,1547,0,0,0),(1548,'','','zxcv','2021-08-23 13:28:30','2021-08-23 13:28:30',NULL,1548,0,0,0),(1549,'1','2','zxcv','2021-08-23 14:21:14','2021-08-23 14:21:14',NULL,1545,1,1,0),(1550,'123','412','zxcv','2021-08-27 11:14:51','2021-08-27 11:14:51',NULL,1550,0,0,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_attach`
--

LOCK TABLES `board_attach` WRITE;
/*!40000 ALTER TABLE `board_attach` DISABLE KEYS */;
INSERT INTO `board_attach` VALUES ('C:/ssafyyyy/daaff7ad-7860-414d-89f0-c819012c7c2f_impo.png',1505,'2021-07-28 06:22:34','impo.png',17),('C:/ssafyyyy/6ddd711a-418e-4f8b-aed4-10b7cdd19911_09170eb436401a39.gif',1506,'2021-07-28 06:55:46','09170eb436401a39.gif',18),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/board-reply/upload/b535950c-c532-49b5-a5a3-85380d4b3de0_09170eb436401a39.gif',1516,'2021-08-04 12:47:57','09170eb436401a39.gif',19),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/a4366a29-3559-4035-a041-bf1cb280e420_20180620_131955.png',1516,'2021-08-06 11:58:59','20180620_131955.png',23),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/ad9651d1-1bd6-4d95-83d7-2d61d3d53255_1조 보고서 ver.pdf',1516,'2021-08-06 11:59:37','1조 보고서 ver.pdf',24),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/d2062104-8436-4a48-a361-fd8b1ed3347e_09170eb436401a39.gif',1528,'2021-08-10 07:01:19','09170eb436401a39.gif',25),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/c761fe09-bd72-4ee3-b092-a7f3914ace06_09170eb436401a39.gif',1529,'2021-08-10 07:01:30','09170eb436401a39.gif',26),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/91e0d926-102e-4376-a0e0-b1e1fc45ab46_42SEOUL_배경화면.png',1530,'2021-08-10 07:02:00','42SEOUL_배경화면.png',27),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/14dc10ae-0e21-488b-b070-48f0dd35ec56_09170eb436401a39.gif',1530,'2021-08-10 07:02:00','09170eb436401a39.gif',28),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/47fab58b-46b7-4a6e-afa8-9614140fc808_09170eb436401a39.gif',1530,'2021-08-10 07:02:34','09170eb436401a39.gif',29),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/fea35fe6-214d-4c94-91a1-6df1894bfbeb_42SEOUL_배경화면.png',1531,'2021-08-10 07:03:13','42SEOUL_배경화면.png',30),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/9c6c2c7f-e3d6-4439-a3f8-6009247d1b32_09170eb436401a39.gif',1531,'2021-08-10 07:03:13','09170eb436401a39.gif',31),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/f3e6108c-aa5d-4592-9a21-e508a86a1558_42SEOUL_배경화면.png',1532,'2021-08-10 07:03:17','42SEOUL_배경화면.png',32),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/45016daa-d1c8-4fa8-8df3-cb5e38026bd4_09170eb436401a39.gif',1532,'2021-08-10 07:03:17','09170eb436401a39.gif',33),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/26506dad-77ff-4904-ad68-3c5ee2c9d136_42SEOUL_배경화면.png',1533,'2021-08-10 07:03:50','42SEOUL_배경화면.png',34),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/be8fa930-fa95-4ed6-91c6-236e4d7f5163_09170eb436401a39.gif',1533,'2021-08-10 07:03:50','09170eb436401a39.gif',35),('C:/helngers/77cdd18a-fd0b-4ec2-9d60-f1d590660950_09170eb436401a39.gif',1542,'2021-08-18 18:19:38','09170eb436401a39.gif',36);
/*!40000 ALTER TABLE `board_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board_like`
--

DROP TABLE IF EXISTS `board_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_like` (
  `like_no` int NOT NULL AUTO_INCREMENT,
  `like_type` int NOT NULL,
  `type_no` int NOT NULL,
  `user_no` int NOT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_no`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='좋아요';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_like`
--

LOCK TABLES `board_like` WRITE;
/*!40000 ALTER TABLE `board_like` DISABLE KEYS */;
INSERT INTO `board_like` VALUES (1,0,0,0,'2021-08-06 13:39:16'),(2,0,0,0,'2021-08-06 13:45:29'),(3,0,0,0,'2021-08-06 13:45:39'),(4,0,0,0,'2021-08-06 13:47:57'),(5,0,0,0,'2021-08-06 13:48:02'),(16,1,1516,100,'2021-08-09 11:14:23'),(19,1,1518,100,'2021-08-10 07:28:39'),(21,0,0,0,'2021-08-19 10:15:15'),(23,0,0,0,'2021-08-19 10:17:14'),(24,0,0,0,'2021-08-19 10:18:01'),(25,0,0,0,'2021-08-19 10:18:56'),(26,0,0,0,'2021-08-19 10:19:16'),(27,0,0,0,'2021-08-19 10:27:53'),(28,0,0,0,'2021-08-19 10:28:11'),(29,0,0,0,'2021-08-19 10:28:18');
/*!40000 ALTER TABLE `board_like` ENABLE KEYS */;
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
  `group_no` int NOT NULL DEFAULT '0',
  `depth_no` int NOT NULL DEFAULT '0',
  `seq_no` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`reply_no`),
  KEY `board_no_fk_idx` (`board_no`),
  CONSTRAINT `board_no_fk` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_reply`
--

LOCK TABLES `board_reply` WRITE;
/*!40000 ALTER TABLE `board_reply` DISABLE KEYS */;
INSERT INTO `board_reply` VALUES (90,'원본','원본',1518,'2021-08-09 14:53:40',90,0,0),(91,'90 답글','90 답글',1518,'2021-08-09 14:53:52',90,1,1),(92,'답글2','답글2',1518,'2021-08-09 14:54:00',90,1,2),(93,'123','123',1518,'2021-08-09 14:58:44',90,1,3),(94,'1234','1234',1518,'2021-08-10 06:37:12',94,0,0),(95,'1234','1234',1518,'2021-08-10 06:42:27',94,1,1),(96,'12341','12344',1518,'2021-08-10 06:45:11',94,1,2),(99,'asdf','asdf',1527,'2021-08-10 06:59:17',99,0,0),(100,'1234','1234',1518,'2021-08-11 11:14:32',100,0,0),(101,'대댓글','대댓글',1518,'2021-08-11 11:15:22',100,1,1),(102,'대대댓글','대대댓글',1518,'2021-08-11 11:17:15',100,1,2),(104,'asdf','asdf',1527,'2021-08-11 11:29:38',104,0,0),(105,'12341','12344',1518,'2021-08-11 11:31:40',94,1,3),(106,'12341','12344',1518,'2021-08-11 11:33:51',94,2,3),(107,'12341','12344',1518,'2021-08-11 11:35:30',100,2,2),(108,'12341','12344',1518,'2021-08-11 11:35:40',100,2,1),(110,'5','6161',1547,'2021-08-23 12:43:45',0,0,0),(112,'hjkl','`123123123',1550,'2021-08-27 12:33:03',0,0,0),(113,'hjkl','asdfasf',1550,'2021-08-27 12:37:34',0,0,0),(115,'hjkl','g',1550,'2021-08-27 12:37:46',0,0,0);
/*!40000 ALTER TABLE `board_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenges`
--

DROP TABLE IF EXISTS `challenges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenges` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenges`
--

LOCK TABLES `challenges` WRITE;
/*!40000 ALTER TABLE `challenges` DISABLE KEYS */;
/*!40000 ALTER TABLE `challenges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medals`
--

DROP TABLE IF EXISTS `medals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medals` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `goal` int DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medals`
--

LOCK TABLES `medals` WRITE;
/*!40000 ALTER TABLE `medals` DISABLE KEYS */;
/*!40000 ALTER TABLE `medals` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'abcd','$2a$10$4/eXF2Jdn/gx2TitbbZRmu2mMDb/R0JfU3xlsWfCJsIHxeZSRmsp.','abd','2021-07-19 08:26:47','2021-07-19 08:26:47','1',NULL,NULL),(2,'abcde','$2a$10$TXHka0d0wKiV4xw4O5hXmeOkTiyjmn/CQ4T697SNj9a2Z8HHUDi.O','abcde','2021-07-20 01:30:24','2021-07-20 01:30:24','1',NULL,NULL),(3,'zxcv','$2a$10$eOB952PDCVm7UWFw/6Cebu6sR14p29YMdc4yMxRBQSlDE/WLVdrk2','zxcv','2021-07-20 01:34:34','2021-07-20 01:34:34','1',NULL,NULL),(4,'asdf','$2a$10$eHLIkH.m5YHdWWKpVIXq8uibwGT5WcQWTiA/PCUb4vURpMeN3lARi','asdf','2021-07-20 01:38:09','2021-07-20 01:38:09','1','ssibal@naver.com',NULL),(5,'wert','$2a$10$hwkPXrDX4FglWeAArEq6aeXnUoLSrhDwYH1XnuXvjk1orrV127fHC','wert','2021-07-20 02:04:38','2021-07-20 02:04:38','1','wert@naver.com',NULL),(6,'wert','$2a$10$HvJJ7wWt8RsN2TtsYUm7G.c5PTddT4pfdVobGd/iG4Rac0HHVooGu','wert','2021-07-20 02:08:21','2021-07-20 02:08:21','1','wert@naver.com',NULL),(7,'wert','$2a$10$Rs9ynYokcYxJH6wxCYHZtuBCUEKD4auUmZPQaNltyl4G5MXziQf/S','wert','2021-07-20 02:11:01','2021-07-20 02:11:01','1','wert',NULL),(8,'test','$2a$10$Ozg4B/SH7RfBALcqMt/XYuo2GnnTnbdxMVg2BXJSJ4nPRUjem8du2','test','2021-07-21 11:30:29','2021-07-21 11:30:29','1',NULL,NULL),(9,'wert','$2a$10$ZVpEfvhRYuoAvI64ROX9yuxrC5YAgbPco5AGRNgU0XdUMqMhXv.YW','wert','2021-07-23 10:01:07','2021-07-23 10:01:07','1',NULL,NULL),(10,'wert','$2a$10$cYFNN.GDAYk2dhKTh3BDouE5A4Lhc/o9kZTIiUhLniHvbdtGABUyK','wert','2021-07-23 10:01:24','2021-07-23 10:01:24','1',NULL,NULL),(11,'test1111','$2a$10$ZGDgR6GRRHevWwZn0/i5JOFmbeCey4zEZ/q2EZ8h3UOZByhiq3TvW','test','2021-07-28 13:15:47','2021-07-28 13:15:47','1',NULL,NULL),(12,'uiop','$2a$10$Qh.GJWKbdyWZyqD3Gfg9qu1ZYuEq7i/bLel1.OaTIxLlfyr1IasKe','uiop','2021-08-27 12:16:21','2021-08-27 12:16:21','1',NULL,NULL),(13,'hjkl','$2a$10$ornb72UxeIKCmAiTTJehxu/SPGLi1D.VPrKclp46lQm5M/OlbdWou','qwer','2021-08-27 12:17:49','2021-08-27 12:17:49','1',NULL,NULL);
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

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment_count` int DEFAULT '0',
  `count` int DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `introduce` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `level` int DEFAULT '1',
  `nickname` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `period` int DEFAULT NULL,
  `point` int DEFAULT '0',
  `purpose` int DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'aloha'
--

--
-- Dumping routines for database 'aloha'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-24 23:28:04
