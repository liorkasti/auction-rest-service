-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auction_item_bid`
--

DROP TABLE IF EXISTS `auction_item_bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `auction_item_bid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bidder_id` int(11) NOT NULL,
  `auction_item_id` int(11) DEFAULT NULL,
  `bid_price` int(11) DEFAULT NULL,
  `bid_ts` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `AuctionItemID_FK_idx` (`auction_item_id`),
  KEY `UserID_FK_idx` (`bidder_id`),
  CONSTRAINT `AuctionItemID_FK` FOREIGN KEY (`auction_item_id`) REFERENCES `auction_item` (`id`),
  CONSTRAINT `UserID_FK` FOREIGN KEY (`bidder_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_item_bid`
--

LOCK TABLES `auction_item_bid` WRITE;
/*!40000 ALTER TABLE `auction_item_bid` DISABLE KEYS */;
INSERT INTO `auction_item_bid` VALUES (1,8,2,42,'2018-08-30'),(2,8,3,53,'2018-08-30'),(4,8,9,80,'2018-08-30');
/*!40000 ALTER TABLE `auction_item_bid` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-30 16:31:19
