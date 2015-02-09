CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `account_number` int(10) unsigned DEFAULT NULL,
  `account_type` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `mid_name` varchar(45) DEFAULT NULL,
  `id_number` varchar(45) DEFAULT NULL,
  `phone1` int(11) DEFAULT NULL,
  `phone2` int(11) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_number_UNIQUE` (`id_number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (123,'saving','Minh','Nguyen','Anh','024939082',1699988263,1699988263,'Bui Dinh Tuy','Bui Dinh Tuy','minh@gmail.com','active',1),(234,'saving','Ngan','Pham','Le','12345',1699988263,1699988263,'bach Dang','bach Dang','ngan@gmail.com','active',3),(345,'saving','Phu','Nguyen','Xuan','23456',1699988263,1699988263,'123','123','123','active',4);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interestrate`
--

DROP TABLE IF EXISTS `interestrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interestrate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `savingaccount_type` varchar(45) DEFAULT NULL,
  `interest_rate` float DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `month` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interestrate`
--

LOCK TABLES `interestrate` WRITE;
/*!40000 ALTER TABLE `interestrate` DISABLE KEYS */;
INSERT INTO `interestrate` VALUES (1,'9 months',8,'VND',9),(2,'3 months',6,'VND',3),(3,'0 month',1,'VND',0),(4,'6 months',7,'VND',4),(5,'12 months',9,'VND',12),(6,'36 months',9,'VND',36);
/*!40000 ALTER TABLE `interestrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savingaccount`
--

DROP TABLE IF EXISTS `savingaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `savingaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `savingaccount_number` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `balance_amount` float DEFAULT NULL,
  `repeatable` varchar(11) DEFAULT NULL,
  `interest_rate_id` int(11) NOT NULL,
  `state` varchar(45) DEFAULT NULL,
  `date_start` varchar(45) DEFAULT NULL,
  `date_end` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`interest_rate_id`),
  UNIQUE KEY `savingaccount_number` (`savingaccount_number`),
  KEY `fk_SavingAccount_InterestRate_idx` (`interest_rate_id`),
  KEY `fk_SavingAccount_Customer1_idx` (`customer_id`),
  CONSTRAINT `fk_SavingAccount_Customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_SavingAccount_InterestRate` FOREIGN KEY (`interest_rate_id`) REFERENCES `interestrate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savingaccount`
--

LOCK TABLES `savingaccount` WRITE;
/*!40000 ALTER TABLE `savingaccount` DISABLE KEYS */;
INSERT INTO `savingaccount` VALUES (145,123562,1,2100440,'1',1,'active','09/02/2015 08:51:35','09/11/2015 08:51:35'),(146,123680,1,2000000,'1',1,'active','01/01/2015 01:24:45','01/10/2015 01:24:45'),(147,123699,1,1200000,'1',1,'new','01/02/2015 01:25:05','01/11/2015 01:25:05'),(148,123556,1,100000,'1',1,'deactive','01/02/2015 01:26:25','01/11/2015 01:26:25'),(149,123785,1,4000830,'1',1,'active','08/02/2015 01:28:10','08/11/2015 01:28:10'),(150,123767,3,200000,'1',1,'hold','02/02/2015 01:29:55','02/11/2015 01:29:55'),(151,123568,1,12000000,'1',1,'new','02/02/2015 08:56:10','02/11/2015 08:56:10'),(152,123735,1,2000000,'1',1,'new','02/02/2015 08:56:54','02/11/2015 08:56:54'),(153,123687,1,1111,'1',1,'new','02/02/2015 09:01:44','02/11/2015 09:01:44'),(154,123660,1,12322,'1',1,'hold','02/02/2015 09:03:10','02/11/2015 09:03:10'),(155,123603,1,2233,'1',1,'new','02/02/2015 09:04:19','02/11/2015 09:04:19');
/*!40000 ALTER TABLE `savingaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(45) NOT NULL,
  `amount` float DEFAULT NULL,
  `date_start` varchar(50) DEFAULT NULL,
  `date_end` varchar(50) DEFAULT NULL,
  `saving_account_id` int(11) NOT NULL,
  `state` varchar(45) DEFAULT NULL,
  `current_balance` float DEFAULT NULL,
  `after_balance` float DEFAULT NULL,
  PRIMARY KEY (`id`,`saving_account_id`),
  KEY `fk_Transaction_SavingAccount1_idx` (`saving_account_id`),
  CONSTRAINT `fk_Transaction_SavingAccount1` FOREIGN KEY (`saving_account_id`) REFERENCES `savingaccount` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (132,'deposit',2000000,'08/02/2015 01:24:35','08/02/2015 01:25:29',145,'done',0,2000000),(133,'deposit',2000000,'08/02/2015 01:24:45','08/02/2015 01:25:30',146,'done',0,2000000),(134,'deposit',1200000,'08/02/2015 01:25:06','',147,'new',0,1200000),(135,'deposit',100000,'08/02/2015 01:25:54','09/02/2015 08:51:35',145,'done',2000000,2100440),(136,'deposit',100000,'08/02/2015 01:26:25','',148,'deny',0,100000),(137,'deposit',5000000,'08/02/2015 01:26:49','08/02/2015 01:27:03',149,'done',0,5000000),(138,'withdraw',1000000,'08/02/2015 01:27:55','08/02/2015 01:28:10',149,'done',5000830,4000830),(139,'deposit',200000,'08/02/2015 01:29:55','08/02/2015 01:30:06',150,'deny',0,200000),(140,'deposit',12000000,'09/02/2015 08:56:10','',151,'new',0,12000000),(141,'deposit',2000000,'09/02/2015 08:56:54','',152,'new',0,2000000),(142,'deposit',1123120,'09/02/2015 08:57:59','09/02/2015 08:58:13',145,'deny',2100440,0),(143,'deposit',1111,'09/02/2015 09:01:44','',153,'new',0,1111),(144,'deposit',12322,'09/02/2015 09:03:10','',154,'new',0,12322),(145,'deposit',2233,'09/02/2015 09:04:19','',155,'new',0,2233);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_has_user`
--

DROP TABLE IF EXISTS `transaction_has_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_has_user` (
  `Transaction_T_ID` int(11) NOT NULL,
  `Transaction_SavingAccount_S_ID` int(11) NOT NULL,
  `User_U_ID` int(11) NOT NULL,
  PRIMARY KEY (`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`,`User_U_ID`),
  KEY `fk_Transaction_has_User_User1_idx` (`User_U_ID`),
  KEY `fk_Transaction_has_User_Transaction1_idx` (`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`),
  CONSTRAINT `fk_Transaction_has_User_Transaction1` FOREIGN KEY (`Transaction_T_ID`, `Transaction_SavingAccount_S_ID`) REFERENCES `transaction` (`id`, `saving_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_has_User_User1` FOREIGN KEY (`User_U_ID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_has_user`
--

LOCK TABLES `transaction_has_user` WRITE;
/*!40000 ALTER TABLE `transaction_has_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_has_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionuser`
--

DROP TABLE IF EXISTS `transactionuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactionuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`transaction_id`),
  KEY `fk_Transaction_has_User1_User1_idx` (`user_id`),
  KEY `fk_TransactionUser_Transaction1_idx` (`transaction_id`),
  CONSTRAINT `fk_TransactionUser_Transaction1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_Transaction_has_User1_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionuser`
--

LOCK TABLES `transactionuser` WRITE;
/*!40000 ALTER TABLE `transactionuser` DISABLE KEYS */;
INSERT INTO `transactionuser` VALUES (1,135,10),(2,138,10),(3,142,10);
/*!40000 ALTER TABLE `transactionuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(150) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_name`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'ngan','$2a$10$YdSQO8MewQR8Rsrp/8yGee2iV1AfXKh713.A1kdObkZtqvP8HM9aK',1),(3,'admin123','$2a$10$YdSQO8MewQR8Rsrp/8yGee2iV1AfXKh713.A1kdObkZtqvP8HM9aK',1),(5,'user2','$2a$10$YdSQO8MewQR8Rsrp/8yGee2iV1AfXKh713.A1kdObkZtqvP8HM9aK',1),(10,'minh','$2a$10$YdSQO8MewQR8Rsrp/8yGee2iV1AfXKh713.A1kdObkZtqvP8HM9aK',1),(12,'support1','$2a$10$YdSQO8MewQR8Rsrp/8yGee2iV1AfXKh713.A1kdObkZtqvP8HM9aK',1),(13,'support2','$2a$10$YdSQO8MewQR8Rsrp/8yGee2iV1AfXKh713.A1kdObkZtqvP8HM9aK',1),(14,'admin','$2a$10$YdSQO8MewQR8Rsrp/8yGee2iV1AfXKh713.A1kdObkZtqvP8HM9aK',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_transaction`
--

DROP TABLE IF EXISTS `user_has_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_transaction` (
  `User_U_ID` int(11) NOT NULL,
  `Transaction_T_ID` int(11) NOT NULL,
  `Transaction_SavingAccount_S_ID` int(11) NOT NULL,
  `Transaction_User_U_ID` int(11) NOT NULL,
  PRIMARY KEY (`User_U_ID`,`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`,`Transaction_User_U_ID`),
  KEY `fk_User_has_Transaction_Transaction1_idx` (`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`,`Transaction_User_U_ID`),
  KEY `fk_User_has_Transaction_User1_idx` (`User_U_ID`),
  CONSTRAINT `fk_User_has_Transaction_Transaction1` FOREIGN KEY (`Transaction_T_ID`, `Transaction_SavingAccount_S_ID`) REFERENCES `transaction` (`id`, `saving_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Transaction_User1` FOREIGN KEY (`User_U_ID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_transaction`
--

LOCK TABLES `user_has_transaction` WRITE;
/*!40000 ALTER TABLE `user_has_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_has_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `ROLE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `fk_userrrrr_idx` (`user_name`),
  CONSTRAINT `fk_userrole` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (10,'minh','support'),(11,'admin123','admin'),(13,'ngan','admin'),(14,'support1','support'),(15,'support2','support'),(16,'user2','support'),(20,'admin','admin');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-09  9:05:15
