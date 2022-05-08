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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_attach`
--

LOCK TABLES `board_attach` WRITE;
/*!40000 ALTER TABLE `board_attach` DISABLE KEYS */;
INSERT INTO `board_attach` VALUES ('C:/ssafyyyy/daaff7ad-7860-414d-89f0-c819012c7c2f_impo.png',1505,'2021-07-28 06:22:34','impo.png',17),('C:/ssafyyyy/6ddd711a-418e-4f8b-aed4-10b7cdd19911_09170eb436401a39.gif',1506,'2021-07-28 06:55:46','09170eb436401a39.gif',18),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/board-reply/upload/b535950c-c532-49b5-a5a3-85380d4b3de0_09170eb436401a39.gif',1516,'2021-08-04 12:47:57','09170eb436401a39.gif',19),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/a4366a29-3559-4035-a041-bf1cb280e420_20180620_131955.png',1516,'2021-08-06 11:58:59','20180620_131955.png',23),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/ad9651d1-1bd6-4d95-83d7-2d61d3d53255_1조 보고서 ver.pdf',1516,'2021-08-06 11:59:37','1조 보고서 ver.pdf',24),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/d2062104-8436-4a48-a361-fd8b1ed3347e_09170eb436401a39.gif',1528,'2021-08-10 07:01:19','09170eb436401a39.gif',25),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/c761fe09-bd72-4ee3-b092-a7f3914ace06_09170eb436401a39.gif',1529,'2021-08-10 07:01:30','09170eb436401a39.gif',26),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/91e0d926-102e-4376-a0e0-b1e1fc45ab46_42SEOUL_배경화면.png',1530,'2021-08-10 07:02:00','42SEOUL_배경화면.png',27),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/14dc10ae-0e21-488b-b070-48f0dd35ec56_09170eb436401a39.gif',1530,'2021-08-10 07:02:00','09170eb436401a39.gif',28),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/47fab58b-46b7-4a6e-afa8-9614140fc808_09170eb436401a39.gif',1530,'2021-08-10 07:02:34','09170eb436401a39.gif',29),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/fea35fe6-214d-4c94-91a1-6df1894bfbeb_42SEOUL_배경화면.png',1531,'2021-08-10 07:03:13','42SEOUL_배경화면.png',30),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/9c6c2c7f-e3d6-4439-a3f8-6009247d1b32_09170eb436401a39.gif',1531,'2021-08-10 07:03:13','09170eb436401a39.gif',31),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/f3e6108c-aa5d-4592-9a21-e508a86a1558_42SEOUL_배경화면.png',1532,'2021-08-10 07:03:17','42SEOUL_배경화면.png',32),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/45016daa-d1c8-4fa8-8df3-cb5e38026bd4_09170eb436401a39.gif',1532,'2021-08-10 07:03:17','09170eb436401a39.gif',33),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/26506dad-77ff-4904-ad68-3c5ee2c9d136_42SEOUL_배경화면.png',1533,'2021-08-10 07:03:50','42SEOUL_배경화면.png',34),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/rest-board/upload/be8fa930-fa95-4ed6-91c6-236e4d7f5163_09170eb436401a39.gif',1533,'2021-08-10 07:03:50','09170eb436401a39.gif',35),('C:/helngers/77cdd18a-fd0b-4ec2-9d60-f1d590660950_09170eb436401a39.gif',1542,'2021-08-18 18:19:38','09170eb436401a39.gif',36),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/layout/upload/d9332c9e-30d2-42c5-8441-d5a4a22ae102_12.png',1553,'2022-05-07 15:54:05','12.png',37),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/layout/upload/0344bdd0-098d-4118-b342-92af0bb81e7c_12.png',1554,'2022-05-07 15:55:01','12.png',38),('C:/ALOHA CAMPUS/workspace/SPRING_CYH/board-reply/upload/332bceac-4986-4d99-a47e-e24754a6991e_12.png',1555,'2022-05-07 15:56:38','12.png',39);
/*!40000 ALTER TABLE `board_attach` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-08  1:29:01
