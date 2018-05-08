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
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'Belgium','admin@hotmail.com','admin','Male','admin','admin','admin',0),(2,0,'Belgium','user@hotmail.com','user','Female','user','user','user',0),(5,0,'','kenbauwens@hotmail.com','Ken','male','Bauwens','test','newaccount',0),(6,0,'belgium','test@hotmail.com','test','test','test','test','ff',0),(7,0,'ken','ken','ken','ken','ken','ken','ken',0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `accountcategoryjunction`
--

LOCK TABLES `accountcategoryjunction` WRITE;
/*!40000 ALTER TABLE `accountcategoryjunction` DISABLE KEYS */;
INSERT INTO `accountcategoryjunction` VALUES (1,1),(2,1),(3,2),(4,2),(5,2);
/*!40000 ALTER TABLE `accountcategoryjunction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'adminBoard',1,1,0),(2,'adminBoard2',2,1,0),(3,'userBoard',3,2,0),(4,'userBoard2',4,2,0),(5,'userBoard3',5,2,0),(6,'Testboard',3,1,0),(7,'myBoard',2,6,0),(8,'tt',2,1,0),(9,';',3,1,0);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `boardfollowers`
--

LOCK TABLES `boardfollowers` WRITE;
/*!40000 ALTER TABLE `boardfollowers` DISABLE KEYS */;
INSERT INTO `boardfollowers` VALUES (1,1,0),(2,1,1);
/*!40000 ALTER TABLE `boardfollowers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,NULL,'African'),(2,NULL,'Belgian'),(3,NULL,'French'),(4,NULL,'Indian'),(5,NULL,'Spanish'),(6,NULL,'testCategory');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `peoplefollower`
--

LOCK TABLES `peoplefollower` WRITE;
/*!40000 ALTER TABLE `peoplefollower` DISABLE KEYS */;
INSERT INTO `peoplefollower` VALUES (1,2,0),(2,1,1);
/*!40000 ALTER TABLE `peoplefollower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pin`
--

LOCK TABLES `pin` WRITE;
/*!40000 ALTER TABLE `pin` DISABLE KEYS */;
INSERT INTO `pin` VALUES (1,'recipe','recipeName',1),(2,'recipe','recipeName',2),(3,'recipe2','recipeName2',1),(4,'recipe3','recipeName3',1),(5,'testpin','rrr',1);
/*!40000 ALTER TABLE `pin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
INSERT INTO `statistics` VALUES (1,'2018-04-24 15:50:30',1),(2,'2018-04-24 15:50:49',1),(3,'2018-04-24 15:50:54',1),(4,'2018-04-24 15:51:00',2),(5,'2018-04-24 15:51:04',2),(6,'2018-05-03 14:42:09',1),(7,'2018-05-04 18:26:16',1),(8,'2018-05-04 18:27:15',5),(9,'2018-05-04 19:39:22',1),(10,'2018-05-04 19:40:06',1),(11,'2018-05-04 19:40:24',1),(12,'2018-05-04 19:40:33',1),(13,'2018-05-04 19:41:02',1),(14,'2018-05-04 19:41:24',1),(15,'2018-05-04 19:41:33',1),(16,'2018-05-04 19:42:27',1),(17,'2018-05-04 19:42:39',1),(18,'2018-05-04 19:44:58',1),(19,'2018-05-04 19:45:58',1),(20,'2018-05-04 19:48:08',1),(21,'2018-05-05 10:51:06',1),(22,'2018-05-05 10:53:31',1),(23,'2018-05-05 10:54:22',1),(24,'2018-05-05 10:55:21',1),(25,'2018-05-05 10:56:57',1),(26,'2018-05-05 10:58:21',1),(27,'2018-05-05 11:00:30',1),(28,'2018-05-05 12:08:38',1),(29,'2018-05-05 12:08:41',1),(30,'2018-05-05 12:11:25',1),(31,'2018-05-05 12:19:55',1),(32,'2018-05-05 12:21:31',1),(33,'2018-05-05 12:22:26',1),(34,'2018-05-05 12:23:52',1),(35,'2018-05-05 12:29:46',1),(36,'2018-05-05 12:30:03',1),(37,'2018-05-05 12:31:41',1),(38,'2018-05-05 12:32:27',1),(39,'2018-05-05 12:32:58',1),(40,'2018-05-05 12:33:20',1),(41,'2018-05-05 12:38:13',1),(42,'2018-05-05 12:39:06',1),(43,'2018-05-05 12:44:05',1),(44,'2018-05-05 12:44:27',1),(45,'2018-05-05 13:36:41',1),(46,'2018-05-05 13:37:07',1),(47,'2018-05-05 14:03:03',1),(48,'2018-05-05 14:05:37',2),(49,'2018-05-05 14:25:41',6),(50,'2018-05-05 15:53:52',1),(51,'2018-05-06 10:03:08',1),(52,'2018-05-06 10:04:02',7),(53,'2018-05-06 10:05:25',7),(54,'2018-05-06 10:05:48',7),(55,'2018-05-06 10:25:07',7),(56,'2018-05-06 10:36:09',7),(57,'2018-05-06 10:37:49',7),(58,'2018-05-06 10:38:39',7),(59,'2018-05-06 10:40:08',7),(60,'2018-05-06 10:45:03',7),(61,'2018-05-06 10:49:06',7),(62,'2018-05-06 10:49:22',7),(63,'2018-05-06 10:50:10',7),(64,'2018-05-06 10:50:45',7),(65,'2018-05-06 10:51:03',7),(66,'2018-05-06 10:52:29',7),(67,'2018-05-06 10:52:53',7),(68,'2018-05-06 10:53:42',7),(69,'2018-05-07 18:19:06',1),(70,'2018-05-07 21:49:44',1),(71,'2018-05-08 10:56:49',1),(72,'2018-05-08 11:05:35',1),(73,'2018-05-08 11:07:50',1),(74,'2018-05-08 11:09:53',1),(75,'2018-05-08 11:10:40',1),(76,'2018-05-08 11:11:06',1),(77,'2018-05-08 11:14:47',1),(78,'2018-05-08 11:22:26',1),(79,'2018-05-08 11:24:56',1),(80,'2018-05-08 11:37:07',1),(81,'2018-05-08 11:46:29',1),(82,'2018-05-08 11:46:55',1),(83,'2018-05-08 11:47:30',1),(84,'2018-05-08 11:54:56',1),(85,'2018-05-08 11:57:07',1),(86,'2018-05-08 12:00:26',1),(87,'2018-05-08 12:01:15',1),(88,'2018-05-08 12:02:23',1),(89,'2018-05-08 12:03:23',1),(90,'2018-05-08 12:04:00',1),(91,'2018-05-08 15:17:27',1),(92,'2018-05-08 15:18:10',1),(93,'2018-05-08 15:22:32',1),(94,'2018-05-08 15:24:20',1),(95,'2018-05-08 15:25:20',1),(96,'2018-05-08 15:26:24',1),(97,'2018-05-08 15:27:12',1),(98,'2018-05-08 15:30:35',1),(99,'2018-05-08 15:33:35',1),(100,'2018-05-08 15:34:06',1),(101,'2018-05-08 15:35:52',1),(102,'2018-05-08 15:36:27',1),(103,'2018-05-08 15:37:46',1),(104,'2018-05-08 15:38:28',1),(105,'2018-05-08 15:39:16',1),(106,'2018-05-08 15:45:00',1),(107,'2018-05-08 15:57:24',1),(108,'2018-05-08 16:05:03',1),(109,'2018-05-08 16:06:03',1),(110,'2018-05-08 16:07:06',1),(111,'2018-05-08 16:07:30',1),(112,'2018-05-08 19:31:01',1),(113,'2018-05-08 22:54:58',1);
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `useractions`
--

LOCK TABLES `useractions` WRITE;
/*!40000 ALTER TABLE `useractions` DISABLE KEYS */;
INSERT INTO `useractions` VALUES (1,1,1,'2018-05-08 16:10:35'),(2,1,1,'2018-05-08 16:11:47');
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

-- Dump completed on 2018-05-08 23:10:11
