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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (123456,'saving','Minh','Nguyen','Anh','024939082',1699988263,1699988263,'Bui Dinh Tuy','Bui Dinh Tuy','minh@gmail.com','active',1);
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
  `savingaccout_type` varchar(45) DEFAULT NULL,
  `interest_rate` float DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interestrate`
--

LOCK TABLES `interestrate` WRITE;
/*!40000 ALTER TABLE `interestrate` DISABLE KEYS */;
INSERT INTO `interestrate` VALUES (2,'3 months',6,'VND'),(3,'0 month',0.5,'VND');
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
  `customer_id` int(11) DEFAULT NULL,
  `balance_amount` float DEFAULT NULL,
  `repeatable` int(11) DEFAULT NULL,
  `interest_rate_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`interest_rate_id`),
  KEY `fk_SavingAccount_InterestRate_idx` (`interest_rate_id`),
  KEY `fk_SavingAccount_Customer1_idx` (`customer_id`),
  CONSTRAINT `fk_SavingAccount_InterestRate` FOREIGN KEY (`interest_rate_id`) REFERENCES `interestrate` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_SavingAccount_Customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savingaccount`
--

LOCK TABLES `savingaccount` WRITE;
/*!40000 ALTER TABLE `savingaccount` DISABLE KEYS */;
INSERT INTO `savingaccount` VALUES (2,1,20000,0,2);
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
  `amount` float DEFAULT NULL,
  `date_start` varchar(50) DEFAULT NULL,
  `date_end` varchar(50) DEFAULT NULL,
  `saving_account_id` int(11) NOT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`saving_account_id`),
  KEY `fk_Transaction_SavingAccount1_idx` (`saving_account_id`),
  CONSTRAINT `fk_Transaction_SavingAccount1` FOREIGN KEY (`saving_account_id`) REFERENCES `savingaccount` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,25000,'20/1/2015','20/1/2015',2,'new'),(2,10000,'20/1/2015','20/1/2015',2,'active'),(3,20000,'20/1/2015','20/1/2015',2,'new');
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
  `user_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`user_id`,`transaction_id`),
  KEY `fk_Transaction_has_User1_User1_idx` (`user_id`),
  KEY `fk_TransactionUser_Transaction1_idx` (`transaction_id`),
  CONSTRAINT `fk_Transaction_has_User1_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_TransactionUser_Transaction1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionuser`
--

LOCK TABLES `transactionuser` WRITE;
/*!40000 ALTER TABLE `transactionuser` DISABLE KEYS */;
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
  `password` varchar(45) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_name`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'minh123','123',1),(2,'ngan','123',1),(3,'admin123','123',1),(4,'user1','123',1),(5,'user2','123',1),(10,'minh','123',1),(11,'admin','123',1),(12,'support1','123',1),(13,'support2','123',1);
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
  CONSTRAINT `fk_User_has_Transaction_User1` FOREIGN KEY (`User_U_ID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Transaction_Transaction1` FOREIGN KEY (`Transaction_T_ID`, `Transaction_SavingAccount_S_ID`) REFERENCES `transaction` (`id`, `saving_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  KEY `fk_userrrrr_idx` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'minh','admin'),(2,'ngan','admin'),(3,'admin','admin'),(4,'user1','support'),(5,'user2','user'),(6,'minh123','admin'),(7,'admin123','admin'),(8,'support1','support'),(9,'support2','support');
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

-- Dump completed on 2015-01-26 17:40:46
