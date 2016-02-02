-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for fcs_stage
CREATE DATABASE IF NOT EXISTS `fcs_stage` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fcs_stage`;


-- Dumping structure for table fcs_stage.collection_request
CREATE TABLE IF NOT EXISTS `collection_request` (
  `req_name` varchar(50) DEFAULT NULL,
  `req_location` varchar(50) DEFAULT NULL,
  `req_address` varchar(50) DEFAULT NULL,
  `req_contact` varchar(50) DEFAULT NULL,
  `req_quantity` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table fcs_stage.collection_request: ~0 rows (approximately)
DELETE FROM `collection_request`;
/*!40000 ALTER TABLE `collection_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection_request` ENABLE KEYS */;


-- Dumping structure for table fcs_stage.collector_availability
CREATE TABLE IF NOT EXISTS `collector_availability` (
  `user_email` varchar(50),
  `status` varchar(50) DEFAULT NULL,
  `qty` varchar(50) DEFAULT NULL,
  `maxDeleveryTime` varchar(50) DEFAULT NULL,
  `currentLocation` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fcs_stage.collector_availability: ~6 rows (approximately)
DELETE FROM `collector_availability`;
/*!40000 ALTER TABLE `collector_availability` DISABLE KEYS */;
INSERT INTO `collector_availability` (`user_email`, `status`, `qty`, `maxDeleveryTime`, `currentLocation`) VALUES
	('789@gmail.com', 'Ideal', NULL, NULL, NULL),
	('123qew@gmail.com', 'Ideal', NULL, NULL, NULL),
	('a@gmial.com', 'Ideal', NULL, NULL, NULL),
	('b', 'Ideal', NULL, NULL, NULL),
	('c', 'Ideal', NULL, NULL, NULL),
	('d', 'Ideal', NULL, NULL, NULL);
/*!40000 ALTER TABLE `collector_availability` ENABLE KEYS */;


-- Dumping structure for table fcs_stage.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table fcs_stage.user: ~16 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `password`, `token`, `role`, `email`, `mobile`) VALUES
	('1123', NULL, '123', NULL, '1', ''),
	('123', '123', NULL, NULL, '123', '123'),
	('123', '123', NULL, NULL, '1234', '123'),
	('123', '123', NULL, NULL, '12345', '123'),
	('123', '123', NULL, NULL, '123456', '123'),
	('123', '123', NULL, NULL, '1234567', '123'),
	('123', '123', NULL, NULL, '123789', '123'),
	('admin', 'shri', '1049995772', 'admin', '2', ''),
	('shrikant', NULL, NULL, NULL, '3', ''),
	('asd', 'asd', NULL, NULL, 'asd1', 'asd'),
	('shrikant', 'b', NULL, NULL, 'b', '132312'),
	('c', 'c', NULL, NULL, 'c', 'c'),
	('d', 'd', NULL, NULL, 'd', 'd'),
	('prashant ', '789789789', NULL, NULL, 'ghuge.prashant213@gmail.com', '7897897898'),
	('shrikant', 'lkj', NULL, NULL, 'ghuge@gmail.com', '12346'),
	('shrikant', 'qwe', NULL, NULL, 'qwe@gmail.com', '1231231233');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
