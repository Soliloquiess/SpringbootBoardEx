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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'abcd','$2a$10$4/eXF2Jdn/gx2TitbbZRmu2mMDb/R0JfU3xlsWfCJsIHxeZSRmsp.','abd','2021-07-19 08:26:47','2021-07-19 08:26:47','1',NULL,NULL),(2,'abcde','$2a$10$TXHka0d0wKiV4xw4O5hXmeOkTiyjmn/CQ4T697SNj9a2Z8HHUDi.O','abcde','2021-07-20 01:30:24','2021-07-20 01:30:24','1',NULL,NULL),(3,'zxcv','$2a$10$eOB952PDCVm7UWFw/6Cebu6sR14p29YMdc4yMxRBQSlDE/WLVdrk2','zxcv','2021-07-20 01:34:34','2021-07-20 01:34:34','1',NULL,NULL),(4,'asdf','$2a$10$eHLIkH.m5YHdWWKpVIXq8uibwGT5WcQWTiA/PCUb4vURpMeN3lARi','asdf','2021-07-20 01:38:09','2021-07-20 01:38:09','1','ssibal@naver.com',NULL),(5,'wert','$2a$10$hwkPXrDX4FglWeAArEq6aeXnUoLSrhDwYH1XnuXvjk1orrV127fHC','wert','2021-07-20 02:04:38','2021-07-20 02:04:38','1','wert@naver.com',NULL),(6,'wert','$2a$10$HvJJ7wWt8RsN2TtsYUm7G.c5PTddT4pfdVobGd/iG4Rac0HHVooGu','wert','2021-07-20 02:08:21','2021-07-20 02:08:21','1','wert@naver.com',NULL),(7,'wert','$2a$10$Rs9ynYokcYxJH6wxCYHZtuBCUEKD4auUmZPQaNltyl4G5MXziQf/S','wert','2021-07-20 02:11:01','2021-07-20 02:11:01','1','wert',NULL),(8,'test','$2a$10$Ozg4B/SH7RfBALcqMt/XYuo2GnnTnbdxMVg2BXJSJ4nPRUjem8du2','test','2021-07-21 11:30:29','2021-07-21 11:30:29','1',NULL,NULL),(9,'wert','$2a$10$ZVpEfvhRYuoAvI64ROX9yuxrC5YAgbPco5AGRNgU0XdUMqMhXv.YW','wert','2021-07-23 10:01:07','2021-07-23 10:01:07','1',NULL,NULL),(10,'wert','$2a$10$cYFNN.GDAYk2dhKTh3BDouE5A4Lhc/o9kZTIiUhLniHvbdtGABUyK','wert','2021-07-23 10:01:24','2021-07-23 10:01:24','1',NULL,NULL),(11,'test1111','$2a$10$ZGDgR6GRRHevWwZn0/i5JOFmbeCey4zEZ/q2EZ8h3UOZByhiq3TvW','test','2021-07-28 13:15:47','2021-07-28 13:15:47','1',NULL,NULL),(12,'uiop','$2a$10$Qh.GJWKbdyWZyqD3Gfg9qu1ZYuEq7i/bLel1.OaTIxLlfyr1IasKe','uiop','2021-08-27 12:16:21','2021-08-27 12:16:21','1',NULL,NULL),(13,'hjkl','$2a$10$ornb72UxeIKCmAiTTJehxu/SPGLi1D.VPrKclp46lQm5M/OlbdWou','qwer','2021-08-27 12:17:49','2021-08-27 12:17:49','1',NULL,NULL),(14,'123','$2a$10$MAMrN3WgA5mJdgCxVbtLKOY3SMOgKwFxysV5A.rQcHOkd8ZO2LamS','123','2022-05-07 15:50:48','2022-05-07 15:50:48','1',NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-08  1:29:02
