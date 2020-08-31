-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: transferapp
-- ------------------------------------------------------
-- Server version	8.0.18


DELETE FROM `patient`;
DELETE FROM `patHistory`;

--
-- Dumping data for table `user_account`
--
INSERT INTO `patient` (id,address, dob, family, given, phone, sex)
VALUES
(1,'2 Warren Street','1968-06-22','Ferguson','Lucas','387-866-1399','M'),
(2,'745 West Valley Farms Drive','1952-09-27','Rees','Pippa','628-423-0993','F'),
(3,'599 East Garden Ave','1952-11-11','Arnold','Edward','123-727-2779','M'),
(4,'894 Hall Street','1946-11-26','Sharp','Anthony','451-761-8383','M'),
(5,'4 Southampton Road','1958-06-29','Ince','Wendy','802-911-9975','F'),
(6,'40 Sulphur Springs Dr','1949-12-07','Ross','Tracey','131-396-5049','F'),
(7,'12 Cobblestone St','1966-12-31','Wilson','Claire','300-452-1091','F'),
(8,'193 Vale St','1945-06-24','Buckland','Max','833-534-0864','M'),
(9,'12 Beechwood Road','1964-06-18','Clark','Natalie','241-467-9197','F'),
(10,'1202 Bumble Dr','1959-06-28','Bailey','Piers','747-815-0557','M');


