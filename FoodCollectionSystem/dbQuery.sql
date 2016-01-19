-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.42 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for fcs_stage
CREATE DATABASE IF NOT EXISTS `fcs_stage` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `fcs_stage`;


-- Dumping structure for table fcs_stage.user
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fcs_stage.user: ~10 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `mobile`, `email`, `password`, `role`, `token`) VALUES
	('1123', '', '1', NULL, NULL, '123'),
	('123', '123', '123', '123', NULL, NULL),
	('123', '123', '1234', '123', NULL, NULL),
	('123', '123', '12345', '123', NULL, NULL),
	('123', '123', '123456', '123', NULL, NULL),
	('123', '123', '1234567', '123', NULL, NULL),
	('admin', '', '2', 'shri', 'admin', '1102739406'),
	('shrikant', '', '3', NULL, NULL, NULL),
	('asd', 'asd', 'asd', 'asd', NULL, NULL),
	('asd', 'asd', 'asd1', 'asd', NULL, NULL),
	('shrikant', '12346', 'ghuge@gmail.com', 'lkj', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
