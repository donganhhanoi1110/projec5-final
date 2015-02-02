-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 02, 2015 at 05:58 PM
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
(234, 'saving', 'Ngan', 'Nguyen', '123', '123', 123, 123, '123', '123', '123', 'active', 3),
(345, 'saving', 'Phu', 'Nguyen', '123', '234', 123, 123, '123', '123', '123', 'active', 4);

-- --------------------------------------------------------

--
-- Table structure for table `interestrate`
--

CREATE TABLE IF NOT EXISTS `interestrate` (
`id` int(11) NOT NULL,
  `savingaccount_type` varchar(45) DEFAULT NULL,
  `interest_rate` float DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `month` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `interestrate`
--

INSERT INTO `interestrate` (`id`, `savingaccount_type`, `interest_rate`, `currency`, `month`) VALUES
(1, '9 months', 8, 'VND', 9),
(2, '3 months', 6, 'VND', 3),
(3, '0 month', 1, 'VND', 0),
(4, '6 months', 7, 'VND', 4),
(5, '12 months', 9, 'VND', 12),
(6, '36 months', 9, 'VND', 36);

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
  `state` varchar(45) DEFAULT NULL,
  `date_start` varchar(45) DEFAULT NULL,
  `date_end` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=97 ;

--
-- Dumping data for table `savingaccount`
--

INSERT INTO `savingaccount` (`id`, `savingaccount_number`, `customer_id`, `balance_amount`, `repeatable`, `interest_rate_id`, `state`, `date_start`, `date_end`) VALUES
(1, 123458, 1, 8234530, 0, 1, 'active', 'Mon Feb 02 22:20:02 ICT 2015', 'Mon Nov 02 22:20:02 ICT 2015'),
(2, 1234592, 1, 30000, 0, 1, 'active', '22/11/2015', '22/12/2015'),
(89, 123570, 1, 5000000, 0, 1, 'new', '11/02/2015', '02/02/2015'),
(90, 123427, 1, 1000000, 0, 1, 'new', '01/06/2014', '02/02/2015'),
(91, 123469, 1, 100000, 0, 1, 'active', '04/02/2015', '08/02/2015'),
(93, 123604, 1, 111, 0, 1, 'new', '1111111', '1111111'),
(94, 123533, 1, 111111, 0, 1, 'new', '11/02/2015', '18/02/2015'),
(95, 123411, 1, 1111, 0, 1, 'new', '04/02/2015', '09/02/2015'),
(96, 123579, 1, 111111, 0, 1, 'new', '05/02/2015', '10/02/2015');

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
  `state` varchar(45) DEFAULT NULL,
  `current_balance` float DEFAULT NULL,
  `after_balance` float DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=75 ;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `transaction_type`, `amount`, `date_start`, `date_end`, `saving_account_id`, `state`, `current_balance`, `after_balance`) VALUES
(60, 'deposit', 10000, 'Fri Jan 30 10:54:23 ICT 2015', 'Fri Jan 30 11:09:12 ICT 2015', 1, 'done', 80000, 90000),
(61, 'deposit', 10000, 'Fri Jan 30 11:18:55 ICT 2015', 'Fri Jan 30 11:19:14 ICT 2015', 1, 'done', 90000, 100000),
(62, 'deposit', 10000, 'Fri Jan 30 11:19:29 ICT 2015', 'Sun Feb 01 17:41:44 ICT 2015', 1, 'done', 1000000, 5300000),
(67, 'deposit', 1000000, 'Sun Feb 01 22:44:03 ICT 2015', '', 1, 'hold', 0, 0),
(68, 'deposit', 1000000, 'Sun Feb 01 22:49:15 ICT 2015', NULL, 1, 'hold', 5300000, 0),
(69, 'deposit', 1000000, 'Mon Feb 02 00:14:34 ICT 2015', 'Mon Feb 02 00:42:14 ICT 2015', 1, 'done', 5300000, 8357650),
(70, 'deposit', 111, 'Mon Feb 02 21:05:03 ICT 2015', '', 93, 'new', 0, 111),
(71, 'deposit', 111111, 'Mon Feb 02 21:16:49 ICT 2015', '', 94, 'new', 0, 111111),
(72, 'deposit', 1111, 'Mon Feb 02 21:19:29 ICT 2015', '', 95, 'new', 0, 1111),
(73, 'deposit', 111111, 'Mon Feb 02 21:37:14 ICT 2015', '', 96, 'new', 0, 111111),
(74, 'withdraw', 123123, 'Mon Feb 02 22:19:15 ICT 2015', 'Mon Feb 02 22:20:02 ICT 2015', 1, 'done', 8357650, 8234530);

-- --------------------------------------------------------

--
-- Table structure for table `transactionuser`
--

CREATE TABLE IF NOT EXISTS `transactionuser` (
`id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=70 ;

--
-- Dumping data for table `transactionuser`
--

INSERT INTO `transactionuser` (`id`, `transaction_id`, `user_id`) VALUES
(67, 68, 10),
(68, 69, 10),
(69, 74, 10),
(66, 67, 14);

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
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id_number_UNIQUE` (`id_number`);

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
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=97;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=75;
--
-- AUTO_INCREMENT for table `transactionuser`
--
ALTER TABLE `transactionuser`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=70;
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
ADD CONSTRAINT `fk_SavingAccount_Customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_SavingAccount_InterestRate` FOREIGN KEY (`interest_rate_id`) REFERENCES `interestrate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
ADD CONSTRAINT `fk_Transaction_SavingAccount1` FOREIGN KEY (`saving_account_id`) REFERENCES `savingaccount` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
ADD CONSTRAINT `fk_userrole` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
