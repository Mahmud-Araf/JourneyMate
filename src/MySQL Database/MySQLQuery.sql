CREATE DATABASE JourneyMate;

--
-- Table structure for table `Bookings`
--

DROP TABLE IF EXISTS JourneyMate.Bookings;

CREATE TABLE JourneyMate.Bookings (
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
) 


--
-- Table structure for table `Clients`
--

DROP TABLE IF EXISTS JourneyMate.Clients;

CREATE TABLE JourneyMate.Clients (
  `id` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) DEFAULT NULL,
  `ClientName` varchar(100) DEFAULT NULL,
  `MobileNumber` varchar(25) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 


--
-- Table structure for table `TourPackages`
--

DROP TABLE IF EXISTS JourneyMate.TourPackages;

CREATE TABLE JourneyMate.TourPackages (
  `id` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) DEFAULT NULL,
  `PackageName` varchar(255) DEFAULT NULL,
  `District` varchar(255) DEFAULT NULL,
  `SpotName` varchar(255) DEFAULT NULL,
  `SpotPrice` decimal(10,2) DEFAULT NULL,
  `TotalPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 


--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS JourneyMate.Users;

CREATE TABLE JourneyMate.Users (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name` (`Name`),
  UNIQUE KEY `Email` (`Email`)
) 
