-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 28, 2015 at 08:14 PM
-- Server version: 5.5.39
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
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
`id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`account_number`, `account_type`, `first_name`, `last_name`, `mid_name`, `id_number`, `phone1`, `phone2`, `address1`, `address2`, `email`, `state`, `id`) VALUES
(123, 'saving', 'Minh', 'Nguyen', 'Anh', '024939082', 1699988263, 1699988263, 'Bui Dinh Tuy', 'Bui Dinh Tuy', 'minh@gmail.com', 'active', 1),
(234, 'saving', 'Minh', 'Nguyen', '123', '123', 123, 123, '123', '123', '123', 'active', 3),
(345, 'saving', 'Minh', 'Nguyen', '123', '123', 123, 123, '123', '123', '123', 'active', 4);

-- --------------------------------------------------------

--
-- Table structure for table `interestrate`
--

CREATE TABLE IF NOT EXISTS `interestrate` (
`id` int(11) NOT NULL,
  `savingaccount_type` varchar(45) DEFAULT NULL,
  `interest_rate` float DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `interestrate`
--

INSERT INTO `interestrate` (`id`, `savingaccount_type`, `interest_rate`, `currency`) VALUES
(1, '9 months', 9, 'VND'),
(2, '3 months', 3, 'VND'),
(3, '0 month', 0.5, 'VND'),
(4, '6 months', 6, 'VND'),
(5, '12 months', 12, 'VND'),
(6, '36 months', 12, 'VND');

-- --------------------------------------------------------

--
-- Table structure for table `savingaccount`
--

CREATE TABLE IF NOT EXISTS `savingaccount` (
`id` int(11) NOT NULL,
  `savingaccount_number` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `balance_amount` float DEFAULT NULL,
  `repeatable` int(11) DEFAULT NULL,
  `interest_rate_id` int(11) NOT NULL,
  `state` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `savingaccount`
--

INSERT INTO `savingaccount` (`id`, `savingaccount_number`, `customer_id`, `balance_amount`, `repeatable`, `interest_rate_id`, `state`) VALUES
(14, 123456, 1, 10000, 0, 1, 'active');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
`id` int(11) NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `amount` float DEFAULT NULL,
  `date_start` varchar(50) DEFAULT NULL,
  `date_end` varchar(50) DEFAULT NULL,
  `saving_account_id` int(11) NOT NULL,
  `state` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=56 ;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `transaction_type`, `amount`, `date_start`, `date_end`, `saving_account_id`, `state`) VALUES
(40, 'withdraw', 10000, '12/11/1993', '12/11/1993', 14, 'active'),
(41, 'deposit', 123, '123', '123', 14, 'active'),
(42, 'deposit', 12345, '134', '123', 14, 'hold'),
(43, 'deposit', 123, '123', '123', 14, 'hold'),
(44, 'deposit', 123, '123', '123', 14, 'new'),
(45, 'deposit', 123, 'minh', '123', 14, 'new'),
(46, 'deposit', 1234, 'minh2', '123', 14, 'hold'),
(47, 'deposit', 12344, '123', '123', 14, 'active'),
(48, 'deposit', 123333, '123', '123', 14, 'active'),
(49, 'deposit', 12345, 'Minh T&#7841;o', '123', 14, 'hold'),
(50, 'deposit', 123333, '123', '123', 14, 'hold'),
(51, 'deposit', 123333, '123', '123', 14, 'hold'),
(52, 'deposit', 123, 'Minh T&#7841;o', '123', 14, 'hold'),
(53, 'deposit', 123, '123', '123', 14, 'hold'),
(54, 'deposit', 1234, '1234', '1234', 14, 'hold'),
(55, 'deposit', 1234, '1234', '1234', 14, 'active');

-- --------------------------------------------------------

--
-- Table structure for table `transactionuser`
--

CREATE TABLE IF NOT EXISTS `transactionuser` (
`id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `transactionuser`
--

INSERT INTO `transactionuser` (`id`, `transaction_id`, `user_id`) VALUES
(27, 41, 10),
(29, 43, 10),
(30, 44, 10),
(31, 45, 10),
(35, 49, 10),
(45, 46, 10),
(33, 47, 14),
(34, 48, 14),
(36, 50, 14),
(37, 51, 14),
(38, 52, 14),
(39, 53, 14),
(42, 55, 14),
(43, 54, 14),
(44, 46, 14),
(48, 42, 14);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_has_user`
--

CREATE TABLE IF NOT EXISTS `transaction_has_user` (
  `Transaction_T_ID` int(11) NOT NULL,
  `Transaction_SavingAccount_S_ID` int(11) NOT NULL,
  `User_U_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `user_name`, `password`, `enable`) VALUES
(1, 'minh1234', '123', 1),
(2, 'ngan', '123', 1),
(3, 'admin123', '123', 1),
(5, 'user2', '123', 1),
(10, 'minh', '123', 1),
(12, 'support1', '123', 1),
(13, 'support2', '123', 1),
(14, 'admin', '123', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_has_transaction`
--

CREATE TABLE IF NOT EXISTS `user_has_transaction` (
  `User_U_ID` int(11) NOT NULL,
  `Transaction_T_ID` int(11) NOT NULL,
  `Transaction_SavingAccount_S_ID` int(11) NOT NULL,
  `Transaction_User_U_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
`user_role_id` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `ROLE` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `user_name`, `ROLE`) VALUES
(10, 'minh', 'support'),
(11, 'admin123', 'admin'),
(12, 'minh1234', 'admin'),
(13, 'ngan', 'admin'),
(14, 'support1', 'support'),
(15, 'support2', 'support'),
(16, 'user2', 'support'),
(20, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `interestrate`
--
ALTER TABLE `interestrate`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `savingaccount`
--
ALTER TABLE `savingaccount`
 ADD PRIMARY KEY (`id`,`interest_rate_id`), ADD UNIQUE KEY `savingaccount_number` (`savingaccount_number`), ADD KEY `fk_SavingAccount_InterestRate_idx` (`interest_rate_id`), ADD KEY `fk_SavingAccount_Customer1_idx` (`customer_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
 ADD PRIMARY KEY (`id`,`saving_account_id`), ADD KEY `fk_Transaction_SavingAccount1_idx` (`saving_account_id`);

--
-- Indexes for table `transactionuser`
--
ALTER TABLE `transactionuser`
 ADD PRIMARY KEY (`id`,`user_id`,`transaction_id`), ADD KEY `fk_Transaction_has_User1_User1_idx` (`user_id`), ADD KEY `fk_TransactionUser_Transaction1_idx` (`transaction_id`);

--
-- Indexes for table `transaction_has_user`
--
ALTER TABLE `transaction_has_user`
 ADD PRIMARY KEY (`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`,`User_U_ID`), ADD KEY `fk_Transaction_has_User_User1_idx` (`User_U_ID`), ADD KEY `fk_Transaction_has_User_Transaction1_idx` (`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`,`user_name`), ADD UNIQUE KEY `user_name_UNIQUE` (`user_name`);

--
-- Indexes for table `user_has_transaction`
--
ALTER TABLE `user_has_transaction`
 ADD PRIMARY KEY (`User_U_ID`,`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`,`Transaction_User_U_ID`), ADD KEY `fk_User_has_Transaction_Transaction1_idx` (`Transaction_T_ID`,`Transaction_SavingAccount_S_ID`,`Transaction_User_U_ID`), ADD KEY `fk_User_has_Transaction_User1_idx` (`User_U_ID`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
 ADD PRIMARY KEY (`user_role_id`), ADD UNIQUE KEY `user_name` (`user_name`), ADD KEY `fk_userrrrr_idx` (`user_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `interestrate`
--
ALTER TABLE `interestrate`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `savingaccount`
--
ALTER TABLE `savingaccount`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=56;
--
-- AUTO_INCREMENT for table `transactionuser`
--
ALTER TABLE `transactionuser`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `savingaccount`
--
ALTER TABLE `savingaccount`
ADD CONSTRAINT `fk_SavingAccount_Customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
ADD CONSTRAINT `fk_SavingAccount_InterestRate` FOREIGN KEY (`interest_rate_id`) REFERENCES `interestrate` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
ADD CONSTRAINT `fk_Transaction_SavingAccount1` FOREIGN KEY (`saving_account_id`) REFERENCES `savingaccount` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `transactionuser`
--
ALTER TABLE `transactionuser`
ADD CONSTRAINT `fk_TransactionUser_Transaction1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
ADD CONSTRAINT `fk_Transaction_has_User1_User1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `transaction_has_user`
--
ALTER TABLE `transaction_has_user`
ADD CONSTRAINT `fk_Transaction_has_User_Transaction1` FOREIGN KEY (`Transaction_T_ID`, `Transaction_SavingAccount_S_ID`) REFERENCES `transaction` (`id`, `saving_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Transaction_has_User_User1` FOREIGN KEY (`User_U_ID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_has_transaction`
--
ALTER TABLE `user_has_transaction`
ADD CONSTRAINT `fk_User_has_Transaction_Transaction1` FOREIGN KEY (`Transaction_T_ID`, `Transaction_SavingAccount_S_ID`) REFERENCES `transaction` (`id`, `saving_account_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_User_has_Transaction_User1` FOREIGN KEY (`User_U_ID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
ADD CONSTRAINT `fk_userrole` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
