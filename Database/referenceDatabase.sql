CREATE DATABASE  IF NOT EXISTS `p4food` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `p4food`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: p4food
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `Country` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `isBlocked` tinyint(4) NOT NULL DEFAULT '0',
  `gmailId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `gmailId_UNIQUE` (`gmailId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'Belgium','admin@hotmail.com','admin','Male','admin','admin','admin',0,NULL),(2,0,'Belgium','user@hotmail.com','user','Female','user','user','user',0,NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountcategoryjunction`
--

DROP TABLE IF EXISTS `accountcategoryjunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountcategoryjunction` (
  `categoryid` int(11) NOT NULL,
  `accountid` int(11) NOT NULL,
  PRIMARY KEY (`categoryid`,`accountid`),
  KEY `FK_accountcategoryjunction_accountid` (`accountid`),
  CONSTRAINT `FK_accountcategoryjunction_accountid` FOREIGN KEY (`accountid`) REFERENCES `account` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_accountcategoryjunction_categoryid` FOREIGN KEY (`categoryid`) REFERENCES `categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountcategoryjunction`
--

LOCK TABLES `accountcategoryjunction` WRITE;
/*!40000 ALTER TABLE `accountcategoryjunction` DISABLE KEYS */;
INSERT INTO `accountcategoryjunction` VALUES (1,1),(2,1),(5,1),(3,2),(4,2),(5,2);
/*!40000 ALTER TABLE `accountcategoryjunction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminactions`
--

DROP TABLE IF EXISTS `adminactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adminactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(45) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminactions`
--

LOCK TABLES `adminactions` WRITE;
/*!40000 ALTER TABLE `adminactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `adminactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `boardname` varchar(255) NOT NULL,
  `category` int(11) DEFAULT NULL,
  `owner` int(11) NOT NULL,
  `isprivate` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_board_category` (`category`),
  KEY `FK_board_owner` (`owner`),
  CONSTRAINT `FK_board_category` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK_board_owner` FOREIGN KEY (`owner`) REFERENCES `account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'newName',1,1,1),(2,'adminBoard2',2,1,0),(13,'newBoard',3,1,0),(14,'test',2,1,0),(15,'userShouldSeeThis',3,1,0),(16,'userShouldSeeThis2',4,1,0),(22,'userShouldSeeThis4',3,1,0),(23,'YetAnotherNotificationForBoard',4,1,0),(24,'testt',5,1,0),(25,'r',2,1,0),(26,'r',3,1,0),(27,'r',2,1,0);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boardfollowers`
--

DROP TABLE IF EXISTS `boardfollowers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boardfollowers` (
  `Userid` int(11) NOT NULL,
  `Boardid` int(11) NOT NULL,
  `isBlocked` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Userid`,`Boardid`),
  KEY `FK_Boardid_Boardfollow_idx` (`Boardid`),
  CONSTRAINT `FK_Boardid_Boardfollow` FOREIGN KEY (`Boardid`) REFERENCES `board` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Userid_Boardfollow` FOREIGN KEY (`Userid`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardfollowers`
--

LOCK TABLES `boardfollowers` WRITE;
/*!40000 ALTER TABLE `boardfollowers` DISABLE KEYS */;
INSERT INTO `boardfollowers` VALUES (1,1,0),(2,1,0);
/*!40000 ALTER TABLE `boardfollowers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imagelocation` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,NULL,'African'),(2,NULL,'Belgian'),(3,NULL,'French'),(4,NULL,'Indian'),(5,NULL,'Spanish'),(6,NULL,'testCategory');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendrequests`
--

DROP TABLE IF EXISTS `friendrequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendrequests` (
  `requester` int(11) NOT NULL,
  `requested` int(11) NOT NULL,
  PRIMARY KEY (`requester`,`requested`),
  KEY `FK_Requested_friendrequests_idx` (`requested`),
  CONSTRAINT `FK_Requested_friendrequests` FOREIGN KEY (`requested`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Requester_friendrequests` FOREIGN KEY (`requester`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendrequests`
--

LOCK TABLES `friendrequests` WRITE;
/*!40000 ALTER TABLE `friendrequests` DISABLE KEYS */;
/*!40000 ALTER TABLE `friendrequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friends` (
  `user1` int(11) NOT NULL,
  `user2` int(11) NOT NULL,
  PRIMARY KEY (`user1`,`user2`),
  KEY `FK_User2_Friends_idx` (`user2`),
  CONSTRAINT `FK_User1_Friends` FOREIGN KEY (`user1`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_User2_Friends` FOREIGN KEY (`user2`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `isRead` tinyint(4) NOT NULL DEFAULT '0',
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Sender_messages_idx` (`sender`),
  KEY `FK_Receiver_messages_idx` (`receiver`),
  CONSTRAINT `FK_Receiver_messages` FOREIGN KEY (`receiver`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Sender_messages` FOREIGN KEY (`sender`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,1,2,'hELLO','World',0,'2018-05-10 10:00:00'),(2,2,1,'ss','dd',1,'2018-05-10 10:00:00'),(3,1,1,'World','Hello',1,'2018-05-11 15:32:01'),(4,1,1,'World','Hello',1,'2018-05-11 15:45:50'),(5,1,2,'Earth','Hi',1,'2018-05-11 15:51:09'),(6,2,1,'Terestrial','Hey',1,'2018-05-11 15:51:31'),(7,1,2,'hello','hi',0,'2018-05-11 18:36:46'),(8,1,2,'fff','dd',0,'2018-05-12 14:11:24'),(9,1,2,'ff','ff',1,'2018-05-13 23:31:24');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `idnotifications` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `isread` tinyint(4) NOT NULL DEFAULT '0',
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idnotifications`),
  KEY `FK_Creator_Notification_idx` (`creator`),
  KEY `FK_Receiver_Notification_idx` (`receiver`),
  CONSTRAINT `FK_Creator_Notification` FOREIGN KEY (`creator`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Receiver_Notification` FOREIGN KEY (`receiver`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,2,1,3,1,NULL),(2,1,2,1,1,'22'),(3,1,2,1,1,'23'),(4,1,1,2,1,'12'),(5,1,1,2,1,'13'),(6,1,2,2,1,'14'),(7,1,2,1,0,'24'),(8,1,2,1,0,'25'),(9,1,2,1,0,'26'),(10,1,2,1,0,'27');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peoplefollower`
--

DROP TABLE IF EXISTS `peoplefollower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peoplefollower` (
  `User` int(11) NOT NULL,
  `FollowedUser` int(11) NOT NULL,
  `isBlocked` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`User`,`FollowedUser`),
  KEY `FK_UserFollowedKey_idx` (`FollowedUser`),
  CONSTRAINT `FK_UserFollowed_Followers` FOREIGN KEY (`FollowedUser`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Userid_Followers` FOREIGN KEY (`User`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peoplefollower`
--

LOCK TABLES `peoplefollower` WRITE;
/*!40000 ALTER TABLE `peoplefollower` DISABLE KEYS */;
INSERT INTO `peoplefollower` VALUES (1,2,0),(2,1,0);
/*!40000 ALTER TABLE `peoplefollower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pin`
--

DROP TABLE IF EXISTS `pin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe` varchar(255) NOT NULL,
  `recipeName` varchar(255) NOT NULL,
  `board` int(11) NOT NULL,
  `location` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pin_board` (`board`),
  CONSTRAINT `FK_pin_board` FOREIGN KEY (`board`) REFERENCES `board` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pin`
--

LOCK TABLES `pin` WRITE;
/*!40000 ALTER TABLE `pin` DISABLE KEYS */;
INSERT INTO `pin` VALUES (1,'recipe','recipeName',1,''),(2,'recipe','recipeName',2,''),(3,'recipe2','recipeName2',1,''),(4,'recipe3','recipeName3',1,''),(5,'testpin','rrr',1,''),(6,'f','f',1,''),(7,'imageTest','imageTest',1,'C:\\Users\\ken\\p4foodPictures'),(8,'imageTest','imageTest',1,'C:\\Users\\ken\\p4foodPictures'),(9,'${board.getId()}','${board.getId()}',1,'C:\\Users\\ken\\p4foodPictures'),(10,'d','d',1,'C:\\Users\\ken\\p4foodPictures\\O5J9RtKLtYZ68v5JXVeCRYg1tkpXZhL9CHyg8v1kpfs.jpg'),(11,'rec','rec',2,'C:\\Users\\ken\\p4foodPictures\\0qkaVYr.jpg'),(12,'ddd','NewPinForUser',23,'C:\\Users\\ken\\p4foodPictures\\laUneyx.png'),(13,'ff','NewPinForUser2',23,'C:\\Users\\ken\\p4foodPictures\\IMG_20170912_144630.jpg'),(14,'fff','NewPinForUser3',23,'C:\\Users\\ken\\p4foodPictures\\IMG_20170912_145041.jpg');
/*!40000 ALTER TABLE `pin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` datetime NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_statistics_userid` (`userid`),
  CONSTRAINT `FK_statistics_userid` FOREIGN KEY (`userid`) REFERENCES `account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
INSERT INTO `statistics` VALUES (1,'2018-04-24 15:50:30',1),(2,'2018-04-24 15:50:49',1),(3,'2018-04-24 15:50:54',1),(4,'2018-04-24 15:51:00',2),(5,'2018-04-24 15:51:04',2),(6,'2018-05-03 14:42:09',1),(7,'2018-05-04 18:26:16',1),(9,'2018-05-04 19:39:22',1),(10,'2018-05-04 19:40:06',1),(11,'2018-05-04 19:40:24',1),(12,'2018-05-04 19:40:33',1),(13,'2018-05-04 19:41:02',1),(14,'2018-05-04 19:41:24',1),(15,'2018-05-04 19:41:33',1),(16,'2018-05-04 19:42:27',1),(17,'2018-05-04 19:42:39',1),(18,'2018-05-04 19:44:58',1),(19,'2018-05-04 19:45:58',1),(20,'2018-05-04 19:48:08',1),(21,'2018-05-05 10:51:06',1),(22,'2018-05-05 10:53:31',1),(23,'2018-05-05 10:54:22',1),(24,'2018-05-05 10:55:21',1),(25,'2018-05-05 10:56:57',1),(26,'2018-05-05 10:58:21',1),(27,'2018-05-05 11:00:30',1),(28,'2018-05-05 12:08:38',1),(29,'2018-05-05 12:08:41',1),(30,'2018-05-05 12:11:25',1),(31,'2018-05-05 12:19:55',1),(32,'2018-05-05 12:21:31',1),(33,'2018-05-05 12:22:26',1),(34,'2018-05-05 12:23:52',1),(35,'2018-05-05 12:29:46',1),(36,'2018-05-05 12:30:03',1),(37,'2018-05-05 12:31:41',1),(38,'2018-05-05 12:32:27',1),(39,'2018-05-05 12:32:58',1),(40,'2018-05-05 12:33:20',1),(41,'2018-05-05 12:38:13',1),(42,'2018-05-05 12:39:06',1),(43,'2018-05-05 12:44:05',1),(44,'2018-05-05 12:44:27',1),(45,'2018-05-05 13:36:41',1),(46,'2018-05-05 13:37:07',1),(47,'2018-05-05 14:03:03',1),(48,'2018-05-05 14:05:37',2),(50,'2018-05-05 15:53:52',1),(51,'2018-05-06 10:03:08',1),(69,'2018-05-07 18:19:06',1),(70,'2018-05-07 21:49:44',1),(71,'2018-05-08 10:56:49',1),(72,'2018-05-08 11:05:35',1),(73,'2018-05-08 11:07:50',1),(74,'2018-05-08 11:09:53',1),(75,'2018-05-08 11:10:40',1),(76,'2018-05-08 11:11:06',1),(77,'2018-05-08 11:14:47',1),(78,'2018-05-08 11:22:26',1),(79,'2018-05-08 11:24:56',1),(80,'2018-05-08 11:37:07',1),(81,'2018-05-08 11:46:29',1),(82,'2018-05-08 11:46:55',1),(83,'2018-05-08 11:47:30',1),(84,'2018-05-08 11:54:56',1),(85,'2018-05-08 11:57:07',1),(86,'2018-05-08 12:00:26',1),(87,'2018-05-08 12:01:15',1),(88,'2018-05-08 12:02:23',1),(89,'2018-05-08 12:03:23',1),(90,'2018-05-08 12:04:00',1),(91,'2018-05-08 15:17:27',1),(92,'2018-05-08 15:18:10',1),(93,'2018-05-08 15:22:32',1),(94,'2018-05-08 15:24:20',1),(95,'2018-05-08 15:25:20',1),(96,'2018-05-08 15:26:24',1),(97,'2018-05-08 15:27:12',1),(98,'2018-05-08 15:30:35',1),(99,'2018-05-08 15:33:35',1),(100,'2018-05-08 15:34:06',1),(101,'2018-05-08 15:35:52',1),(102,'2018-05-08 15:36:27',1),(103,'2018-05-08 15:37:46',1),(104,'2018-05-08 15:38:28',1),(105,'2018-05-08 15:39:16',1),(106,'2018-05-08 15:45:00',1),(107,'2018-05-08 15:57:24',1),(108,'2018-05-08 16:05:03',1),(109,'2018-05-08 16:06:03',1),(110,'2018-05-08 16:07:06',1),(111,'2018-05-08 16:07:30',1),(112,'2018-05-08 19:31:01',1),(113,'2018-05-08 22:54:58',1),(114,'2018-05-09 10:35:36',1),(115,'2018-05-09 10:37:26',1),(116,'2018-05-09 10:39:16',1),(117,'2018-05-09 10:56:23',1),(118,'2018-05-09 11:39:39',1),(119,'2018-05-09 11:50:05',1),(120,'2018-05-09 11:50:54',1),(121,'2018-05-09 11:51:29',1),(122,'2018-05-09 11:52:52',1),(123,'2018-05-09 11:53:52',1),(124,'2018-05-09 11:56:37',1),(125,'2018-05-09 11:59:01',1),(126,'2018-05-09 12:02:37',1),(127,'2018-05-09 12:03:09',1),(128,'2018-05-09 12:09:27',1),(129,'2018-05-09 12:10:36',1),(130,'2018-05-09 12:11:14',1),(131,'2018-05-09 12:11:57',1),(132,'2018-05-09 12:12:13',1),(133,'2018-05-09 12:12:58',1),(134,'2018-05-09 19:52:23',1),(135,'2018-05-09 19:53:17',1),(136,'2018-05-09 19:55:19',1),(137,'2018-05-09 19:59:09',1),(138,'2018-05-09 19:59:48',1),(139,'2018-05-09 20:01:40',1),(140,'2018-05-09 20:02:55',1),(142,'2018-05-10 09:04:55',1),(143,'2018-05-10 10:01:50',1),(144,'2018-05-10 10:02:18',1),(145,'2018-05-10 10:02:52',1),(146,'2018-05-10 10:03:09',1),(147,'2018-05-10 10:03:26',1),(148,'2018-05-10 10:05:45',1),(149,'2018-05-10 10:10:41',1),(150,'2018-05-10 10:11:41',1),(151,'2018-05-10 10:12:44',1),(152,'2018-05-10 10:13:33',1),(153,'2018-05-10 10:17:52',1),(154,'2018-05-10 10:28:53',1),(155,'2018-05-10 10:31:25',1),(156,'2018-05-10 10:33:07',1),(157,'2018-05-10 10:38:07',1),(158,'2018-05-10 10:38:50',1),(159,'2018-05-10 10:47:53',1),(160,'2018-05-10 10:48:54',1),(161,'2018-05-10 10:50:24',1),(162,'2018-05-10 10:58:03',1),(163,'2018-05-10 11:20:25',1),(164,'2018-05-10 11:23:22',1),(165,'2018-05-10 11:31:13',1),(166,'2018-05-10 11:33:30',1),(167,'2018-05-10 11:34:45',1),(168,'2018-05-10 11:41:20',1),(169,'2018-05-10 11:44:33',1),(170,'2018-05-10 11:45:18',1),(171,'2018-05-10 11:51:12',1),(172,'2018-05-10 17:38:39',1),(173,'2018-05-10 17:56:47',1),(174,'2018-05-10 18:16:49',1),(175,'2018-05-10 18:27:19',1),(176,'2018-05-10 18:27:52',1),(177,'2018-05-10 18:28:52',1),(178,'2018-05-10 18:30:09',1),(179,'2018-05-10 19:02:17',1),(180,'2018-05-10 19:04:34',1),(181,'2018-05-10 19:15:10',1),(182,'2018-05-10 19:16:49',1),(183,'2018-05-10 19:17:18',1),(184,'2018-05-10 19:17:45',1),(185,'2018-05-10 19:23:07',1),(186,'2018-05-10 19:37:39',1),(187,'2018-05-10 20:34:19',1),(188,'2018-05-10 20:37:35',1),(189,'2018-05-10 20:40:40',1),(190,'2018-05-10 20:43:39',1),(191,'2018-05-10 20:44:43',1),(192,'2018-05-10 20:50:51',1),(193,'2018-05-10 20:51:09',1),(194,'2018-05-10 20:51:33',1),(195,'2018-05-10 20:52:34',1),(196,'2018-05-10 20:53:02',1),(197,'2018-05-10 20:56:18',1),(198,'2018-05-10 22:23:45',2),(199,'2018-05-10 22:33:50',1),(200,'2018-05-10 22:34:12',1),(201,'2018-05-10 22:38:57',1),(202,'2018-05-10 22:39:22',1),(203,'2018-05-10 22:50:39',1),(204,'2018-05-10 22:55:15',1),(205,'2018-05-11 14:48:37',1),(206,'2018-05-11 14:52:25',1),(207,'2018-05-11 15:10:24',1),(208,'2018-05-11 15:12:11',1),(209,'2018-05-11 15:15:03',1),(210,'2018-05-11 15:17:17',1),(211,'2018-05-11 15:26:32',1),(212,'2018-05-11 15:31:53',1),(213,'2018-05-11 15:38:23',1),(214,'2018-05-11 15:40:01',1),(215,'2018-05-11 15:45:38',1),(216,'2018-05-11 15:47:33',1),(217,'2018-05-11 15:49:14',1),(218,'2018-05-11 15:50:56',1),(219,'2018-05-11 15:51:17',2),(220,'2018-05-11 15:51:36',1),(221,'2018-05-11 15:52:40',1),(222,'2018-05-11 16:27:54',1),(223,'2018-05-11 17:23:12',1),(224,'2018-05-11 17:26:18',1),(225,'2018-05-11 17:26:57',1),(226,'2018-05-11 17:29:35',1),(227,'2018-05-11 17:32:26',1),(228,'2018-05-11 17:36:57',1),(229,'2018-05-11 17:58:09',1),(230,'2018-05-11 18:01:44',1),(231,'2018-05-11 18:02:58',1),(232,'2018-05-11 18:04:53',1),(233,'2018-05-11 18:07:49',1),(234,'2018-05-11 18:30:11',1),(235,'2018-05-11 18:32:24',1),(236,'2018-05-11 18:33:49',1),(237,'2018-05-11 18:36:29',1),(238,'2018-05-11 18:46:17',1),(239,'2018-05-11 19:08:20',1),(240,'2018-05-11 19:18:04',1),(241,'2018-05-11 19:27:55',1),(242,'2018-05-11 19:30:13',1),(243,'2018-05-11 19:33:30',1),(244,'2018-05-11 19:37:33',1),(245,'2018-05-11 19:41:03',1),(246,'2018-05-11 22:50:58',1),(247,'2018-05-11 23:08:11',1),(248,'2018-05-11 23:11:18',1),(249,'2018-05-12 14:11:01',1),(250,'2018-05-12 21:34:40',1),(251,'2018-05-12 23:47:05',1),(252,'2018-05-13 09:49:08',1),(253,'2018-05-13 10:42:19',1),(254,'2018-05-13 10:42:54',1),(255,'2018-05-13 10:43:33',1),(256,'2018-05-13 10:58:18',1),(257,'2018-05-13 10:58:46',1),(258,'2018-05-13 11:00:28',1),(259,'2018-05-13 11:10:24',1),(260,'2018-05-13 11:12:08',2),(261,'2018-05-13 11:12:24',1),(262,'2018-05-13 11:12:35',2),(263,'2018-05-13 11:12:44',1),(264,'2018-05-13 11:12:52',2),(265,'2018-05-13 11:15:29',1),(266,'2018-05-13 12:06:33',1),(267,'2018-05-13 12:07:28',1),(268,'2018-05-13 16:05:44',1),(269,'2018-05-13 16:07:19',1),(270,'2018-05-13 16:19:48',1),(271,'2018-05-13 16:21:18',1),(272,'2018-05-13 16:23:29',1),(273,'2018-05-13 16:27:11',1),(274,'2018-05-13 16:29:15',1),(275,'2018-05-13 16:32:19',1),(276,'2018-05-13 16:33:08',1),(277,'2018-05-13 16:42:42',1),(278,'2018-05-13 17:54:03',1),(279,'2018-05-13 17:54:53',1),(280,'2018-05-13 17:55:53',1),(281,'2018-05-13 17:57:40',1),(282,'2018-05-13 18:02:28',1),(283,'2018-05-13 18:27:59',1),(284,'2018-05-13 18:31:15',1),(285,'2018-05-13 18:34:15',1),(286,'2018-05-13 18:40:43',1),(287,'2018-05-13 18:42:53',1),(288,'2018-05-13 18:44:39',1),(289,'2018-05-13 18:44:58',1),(290,'2018-05-13 18:45:24',1),(291,'2018-05-13 18:45:47',1),(292,'2018-05-13 18:46:45',1),(293,'2018-05-13 20:44:24',1),(294,'2018-05-13 20:45:23',1),(295,'2018-05-13 20:51:15',1),(296,'2018-05-13 20:52:59',1),(297,'2018-05-13 20:54:02',1),(298,'2018-05-13 20:54:49',1),(299,'2018-05-13 20:57:46',1),(300,'2018-05-13 20:59:05',1),(301,'2018-05-13 21:00:42',1),(302,'2018-05-13 21:01:32',1),(303,'2018-05-13 21:02:19',1),(304,'2018-05-13 21:07:11',1),(305,'2018-05-13 21:11:18',1),(306,'2018-05-13 21:12:20',1),(307,'2018-05-13 21:14:26',1),(308,'2018-05-13 21:18:53',1),(309,'2018-05-13 21:22:16',1),(310,'2018-05-13 21:23:04',1),(311,'2018-05-13 21:25:15',1),(312,'2018-05-13 21:26:34',1),(313,'2018-05-13 21:27:17',2),(314,'2018-05-13 21:28:10',1),(315,'2018-05-13 21:29:40',1),(316,'2018-05-13 21:30:01',2),(317,'2018-05-13 21:32:05',1),(318,'2018-05-13 21:35:49',1),(319,'2018-05-13 21:36:14',2),(320,'2018-05-13 21:40:34',1),(321,'2018-05-13 21:45:13',1),(322,'2018-05-13 22:20:46',1),(323,'2018-05-13 22:22:23',1),(324,'2018-05-13 22:27:52',1),(325,'2018-05-13 22:30:26',1),(326,'2018-05-13 22:31:37',1),(327,'2018-05-13 22:38:50',1),(328,'2018-05-13 22:50:47',1),(329,'2018-05-13 23:02:01',1),(330,'2018-05-13 23:11:34',1),(331,'2018-05-13 23:30:50',1),(332,'2018-05-13 23:31:05',2),(333,'2018-05-13 23:31:15',1),(334,'2018-05-13 23:31:56',2),(335,'2018-05-13 23:34:49',1),(336,'2018-05-14 00:01:52',1),(337,'2018-05-14 00:02:52',1);
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useractions`
--

DROP TABLE IF EXISTS `useractions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useractions` (
  `iduseractions` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`iduseractions`),
  KEY `FK_Useractions_User_idx` (`user`),
  KEY `FK_Useractions_Category_idx` (`category`),
  CONSTRAINT `FK_Useractions_Category` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Useractions_User` FOREIGN KEY (`user`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useractions`
--

LOCK TABLES `useractions` WRITE;
/*!40000 ALTER TABLE `useractions` DISABLE KEYS */;
INSERT INTO `useractions` VALUES (1,1,5,'2018-05-14 00:02:01'),(2,1,2,'2018-05-14 00:03:02'),(3,1,3,'2018-05-14 00:03:06'),(4,1,2,'2018-05-14 00:03:20');
/*!40000 ALTER TABLE `useractions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-14  0:04:17
