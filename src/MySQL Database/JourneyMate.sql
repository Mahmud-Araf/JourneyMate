-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: JourneyMate
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Bookings`
--

DROP TABLE IF EXISTS `Bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `BookingID` varchar(50) NOT NULL,
  `ClientName` varchar(100) NOT NULL,
  `PersonNumber` int DEFAULT NULL,
  `BookedPackages` varchar(255) NOT NULL,
  `TotalPrice` decimal(10,2) NOT NULL,
  `Paid` decimal(10,2) NOT NULL,
  `Due` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `BookingID` (`BookingID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bookings`
--

LOCK TABLES `Bookings` WRITE;
/*!40000 ALTER TABLE `Bookings` DISABLE KEYS */;
INSERT INTO `Bookings` VALUES (7,'Araf','S01','Shawon',6,'Sylhet Tour2',27000.00,27000.00,0.00),(9,'Araf','DU01','Fardin',64,'DU Tour',736000.00,400000.00,336000.00),(10,'Araf','DU02','FAIAK',60,'DU Tour',390000.00,300000.00,90000.00),(11,'Araf','Q01','Rafin',5,'Bandarban Tour',35000.00,30000.00,5000.00),(12,'Araf','C01','Faiak Hala',5,'CSEDU Tour',3000.00,1000.00,2000.00);
/*!40000 ALTER TABLE `Bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Clients`
--

DROP TABLE IF EXISTS `Clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) DEFAULT NULL,
  `ClientName` varchar(100) DEFAULT NULL,
  `MobileNumber` varchar(25) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Clients`
--

LOCK TABLES `Clients` WRITE;
/*!40000 ALTER TABLE `Clients` DISABLE KEYS */;
INSERT INTO `Clients` VALUES (3,'Zisan','sami','12345','Chittagong'),(6,'Zisan','Waki','12346','Chittagong'),(7,'Araf','Shawon','123456789','Jatrabari'),(9,'Araf','Zisan','123465769','Dhaka'),(10,'Araf','FAIAK','226856','Dhaka'),(11,'Araf','Rafin','124264685','Lalmonirhat'),(12,'Araf','Fardin','1907491601','Dhaka'),(13,'Araf','SHAWN','126789895','Jatrabari'),(15,'Araf','Emon','536475684','Mymensingh'),(16,'Araf','Faiak Hala','123','Rajarbagh'),(17,'Araf','Rifat 24','1234567','Dhaka');
/*!40000 ALTER TABLE `Clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TourPackages`
--

DROP TABLE IF EXISTS `TourPackages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TourPackages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) DEFAULT NULL,
  `PackageName` varchar(255) DEFAULT NULL,
  `District` varchar(255) DEFAULT NULL,
  `SpotName` varchar(255) DEFAULT NULL,
  `SpotPrice` decimal(10,2) DEFAULT NULL,
  `TotalPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TourPackages`
--

LOCK TABLES `TourPackages` WRITE;
/*!40000 ALTER TABLE `TourPackages` DISABLE KEYS */;
INSERT INTO `TourPackages` VALUES (16,'Araf','Sitakundu Tour','Chittagong','Chandronath Pahar',1500.00,3500.00),(17,'Araf','Sitakundu Tour','Chittagong','Mohamaya Lake',500.00,3500.00),(18,'Araf','Sitakundu Tour','Chittagong','GulayaKhali Sea Beach',1500.00,3500.00),(19,'Araf','Bandarban Tour','Bandarban','BogaLake',2000.00,7000.00),(20,'Araf','Bandarban Tour','Bandarban','NizumJorna',2000.00,7000.00),(21,'Araf','Bandarban Tour','Bandarban','Keokradong',3000.00,7000.00),(30,'Araf','Sylhet Tour2','Sylhet','RatarGhul',2000.00,4500.00),(31,'Araf','Sylhet Tour2','Sylhet','Jaflong',1000.00,4500.00),(32,'Araf','Sylhet Tour2','Sylhet','SadaPathor',1500.00,4500.00),(36,'Araf','DU Tour','Dhaka','VC chattor',500.00,6500.00),(37,'Araf','DU Tour','Dhaka','TSC',1000.00,6500.00),(38,'Araf','DU Tour','Dhaka','CSEDU',5000.00,6500.00),(41,'Araf','CSEDU Tour','Dhaka','3rd Floor',200.00,600.00),(42,'Araf','CSEDU Tour','Dhaka','6th Floor',400.00,600.00);
/*!40000 ALTER TABLE `TourPackages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name` (`Name`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (7,'Zisan','zisan23@gmail.com','12345'),(8,'Araf','almahmudaraf@gmail.com','12345');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-04 13:04:57
