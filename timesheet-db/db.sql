CREATE DATABASE  IF NOT EXISTS `ts_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ts_dev`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ts_dev
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `DEPARTMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPARTMENT_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'TECHNICZNY'),(9,'LOGISTYKA');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entries`
--

DROP TABLE IF EXISTS `entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entries` (
  `ENTRIES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `TIME` time NOT NULL,
  `START` time NOT NULL,
  `END` time NOT NULL,
  `APPROVE` tinyint(1) NOT NULL DEFAULT '0',
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ENTRIES_ID`),
  KEY `ENTRIES_USER_ID_idx` (`USER_ID`),
  CONSTRAINT `ENTRIES_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
INSERT INTO `entries` VALUES (60,'2016-08-30','08:00:00','08:00:00','16:00:00',0,11),(64,'2016-08-28','07:54:00','08:06:00','16:00:00',0,9),(69,'2016-08-26','08:00:00','08:00:00','16:00:00',0,11),(71,'2016-08-19','08:00:00','08:00:00','16:00:00',0,11),(72,'2016-08-23','08:00:00','08:00:00','16:00:00',0,11),(78,'2016-08-30','07:00:00','09:00:00','16:00:00',0,9);
/*!40000 ALTER TABLE `entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `ROLES_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLES_NAME` varchar(25) NOT NULL,
  PRIMARY KEY (`ROLES_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'ROLE_USER'),(1,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(90) NOT NULL,
  `ENABLE` tinyint(1) NOT NULL DEFAULT '0',
  `ROLES_ID` int(11) NOT NULL DEFAULT '0',
  `DEPARTMENTS_ID` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`USER_ID`),
  KEY `USERS_ROLES_ID_idx` (`ROLES_ID`),
  KEY `USERS_DEP_ID_idx` (`DEPARTMENTS_ID`),
  CONSTRAINT `USERS_DEP_ID` FOREIGN KEY (`DEPARTMENTS_ID`) REFERENCES `departments` (`DEPARTMENT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `USERS_ROLES_ID` FOREIGN KEY (`ROLES_ID`) REFERENCES `roles` (`ROLES_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,'marek','$2a$10$Cf7SzVyngwNHXrNBJlDDRuVTMKtRxblzzCEThg3J8NVJZaENpO2w6',1,0,1),(11,'admin','$2a$10$JyNB5qn8qO4K2M.DZJZP6OUJDBaXa/EYHqhpTTvkz0x0YDGVcv7qm',1,1,1),(12,'test','$2a$10$xQUit/QJoDAIMwX/IAVr1.OFHs7TrciUtVHipEHTf1hWjbvWvb6ku',0,0,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-30 15:36:22
