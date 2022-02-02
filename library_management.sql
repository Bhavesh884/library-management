-- MySQL dump 10.13  Distrib 5.1.33, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: library_management
-- ------------------------------------------------------
-- Server version	5.1.33-community

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `pages` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'economics','bk kumavat',200,518),(32,'PYTHON','UNKNOWN',565,865),(229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223),(601,'KHARGOSH','ASHI',30,56),(963,'maths 12th','pata nhi',260,450),(1228,'harry potter','pata nhi',340,230),(1531,'jai mata di','pata nhi',324,2213);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_book`
--

DROP TABLE IF EXISTS `issue_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_book` (
  `bookid` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `page` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `student_name` varchar(45) NOT NULL,
  `fathers_name` varchar(45) NOT NULL,
  `class` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `branch` varchar(45) NOT NULL,
  `ISSUE_DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_book`
--

LOCK TABLES `issue_book` WRITE;
/*!40000 ALTER TABLE `issue_book` DISABLE KEYS */;
INSERT INTO `issue_book` VALUES (2,'economics','bk kumavat',200,518,1374,'hoga koi','pata nhi','12th','pata nhi','MATHS','2019-11-14'),(9,'hp','zjhd',5456,545,1253,'neeraj','dsadsa','11th','dsf','MATHS','2019-11-28'),(601,'KHARGOSH','ASHI',30,56,1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS','2019-11-13'),(1531,'jai mata di','pata nhi',324,2213,351,'bhavesh bhanusali','harish bhanusali','Item 2','kabra colony pipariya','Item 2','2019-11-13'),(9,'hp','zjhd',5456,545,1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS','2019-11-25'),(229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223,1853,'MANALI BHANUSALI','HARISH BHANUSALI','12th','KABRA COLONY PIPARIYA','MATHS','2019-11-18');
/*!40000 ALTER TABLE `issue_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_account`
--

DROP TABLE IF EXISTS `new_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_account` (
  `ID` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `SEC_Q` varchar(45) NOT NULL,
  `ANSWER` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_account`
--

LOCK TABLES `new_account` WRITE;
/*!40000 ALTER TABLE `new_account` DISABLE KEYS */;
INSERT INTO `new_account` VALUES (12,'bhavesh bhanusali','bhavesh','bhavesh','WHAT IS YOUR FAVOURITE COLOUR?','black'),(13,'ashi pandey','ashi','ashi','WHAT IS YOUR FAVOURITE COLOUR?','black'),(14,'bhavika bhanusali','bhavika','bhavika','WHAT IS YOUR FAVOURITE COLOUR?','red'),(15,'pawani','pakhi','gabbu','WHAT IS YOUR NICK NAME?','chipkali'),(16,'dhawal bhanusali','dobbu','w','WHAT IS YOUR FAVOURITE COLOUR?','black'),(17,'AVNI','AVNI SHARMA','AVNI','WHAT IS YOUR FAVOURITE COLOUR?','BLACK'),(18,'deepa','deepa bhanusali','harish','WHAT IS YOUR NICK NAME?','dama'),(19,'shreya tiwari','bhootni','shreya','WHAT IS YOUR FAVOURITE COLOUR?','black'),(20,'aditya pawar','guddu','guddu','WHAT IS YOUR NICK NAME?','lalu'),(21,'zayn malik','zarry','zayn','WHAT IS YOUR NICK NAME?','zannie');
/*!40000 ALTER TABLE `new_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_book`
--

DROP TABLE IF EXISTS `return_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_book` (
  `STUDENT_ID` int(11) NOT NULL,
  `STUDENT_NAME` varchar(45) NOT NULL,
  `FATHERS_NAME` varchar(45) NOT NULL,
  `CLASS` varchar(45) NOT NULL,
  `ADDRESS` varchar(45) NOT NULL,
  `BRANCH` varchar(45) NOT NULL,
  `BOOK_ID` int(11) NOT NULL,
  `BOOK_NAME` varchar(45) NOT NULL,
  `PUBLISHER` varchar(45) NOT NULL,
  `PRICE` int(11) NOT NULL,
  `PAGE` int(11) NOT NULL,
  `ISSUE_DATE` date NOT NULL,
  `RETURN_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_book`
--

LOCK TABLES `return_book` WRITE;
/*!40000 ALTER TABLE `return_book` DISABLE KEYS */;
INSERT INTO `return_book` VALUES (351,'bhavesh bhanusali','harish bhanusali','Item 2','kabra colony pipariya','Item 2',1531,'jai mata di','pata nhi',324,2213,'2019-11-13','2019-11-28'),(1253,'neeraj','dsadsa','11th','dsf','MATHS',9,'hp','zjhd',5456,545,'2019-11-28','2019-11-29'),(1374,'hoga koi','pata nhi','12th','pata nhi','MATHS',2,'economics','bk kumavat',200,518,'2019-11-14','2017-11-08'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',32,'PYTHON','UNKNOWN',565,865,'2019-11-25','2019-11-29'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',601,'KHARGOSH','ASHI',30,56,'2019-11-13','2019-11-25'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',1228,'harry potter','pata nhi',340,230,'2019-11-14','2019-11-24'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',601,'KHARGOSH','ASHI',30,56,'2019-11-13','2019-12-06'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',601,'KHARGOSH','ASHI',30,56,'2019-11-13','2019-12-06'),(1853,'MANALI BHANUSALI','HARISH BHANUSALI','12th','KABRA COLONY PIPARIYA','MATHS',229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223,'2019-11-18','2019-11-28'),(1853,'MANALI BHANUSALI','HARISH BHANUSALI','12th','KABRA COLONY PIPARIYA','MATHS',229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223,'2019-11-18','2019-11-28'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223,'2019-11-26','2019-11-26'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223,'2019-11-05','2019-11-29'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223,'2019-11-27','2019-11-29'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS',229,'THE MISERY OF MY LIFE','NOT FOUND',321,1223,'2019-11-12','2019-11-29');
/*!40000 ALTER TABLE `return_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `class` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `branch` varchar(45) NOT NULL,
  `addmission_year` date DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'jdsd','jgjhh','12th','shsh','Maths',NULL),(12,'fhgf','ghfh','1st','fdgf','Maths',NULL),(1202,'bhavesh bhanusali','harish bhanusali','12th','kabra colony pipariya','Maths',NULL),(1253,'neeraj','dsadsa','11th','dsf','MATHS','2019-11-25'),(1374,'hoga koi','pata nhi','12th','pata nhi','MATHS','2005-06-09'),(1477,'ashi pandey','bablu pandey','12th','vivigiri ward','MATHS','2005-07-13'),(1853,'MANALI BHANUSALI','HARISH BHANUSALI','12th','KABRA COLONY PIPARIYA','MATHS','2001-11-22');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-27  0:40:50
