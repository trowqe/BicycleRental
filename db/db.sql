CREATE DATABASE  IF NOT EXISTS `bicycle_rental` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bicycle_rental`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bicycle_rental
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `bicycle_models`
--

DROP TABLE IF EXISTS `bicycle_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bicycle_models` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `firm_ru` varchar(45) NOT NULL COMMENT 'Производитель',
  `model_ru` varchar(45) DEFAULT NULL COMMENT 'Модель',
  `bicycle_type_id` bigint(11) NOT NULL,
  `notes_ru` varchar(255) DEFAULT NULL COMMENT 'Дополнительная информация',
  `firm_en` varchar(45) NOT NULL,
  `model_en` varchar(45) DEFAULT NULL,
  `notes_en` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Bicycles_BicycleTypes1_idx` (`bicycle_type_id`),
  CONSTRAINT `fk_Bicycles_BicycleTypes1` FOREIGN KEY (`bicycle_type_id`) REFERENCES `bicycle_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='Модели велосипедов';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycle_models`
--

LOCK TABLES `bicycle_models` WRITE;
/*!40000 ALTER TABLE `bicycle_models` DISABLE KEYS */;
INSERT INTO `bicycle_models` VALUES (1,'LTD','Rocco 10',1,'горный, материал рамы: хром-молибденовая сталь, колеса: 26\", количество скоростей: 18, тип тормоза: ободной механический','LTD','Rocco 10','mountain, frame material: chrome-molybdenum steel, wheels: 26 \", number of speeds: 18, type of brake: mechanical'),(2,'LTD','Rocco 30',1,'горный, рама алюминий, колеса 26\", трансмиссия 21 скор., тормоза ободной механический + ободной механический','LTD','Rocco 30','mountain, aluminum frame, 26 \"wheels, 21 speed transmission, brake mechanical rim + mechanical rim'),(3,'FUJI','Nevada 2.0',1,'горный, рама алюминий, колеса 26\", трансмиссия 27 скор., тормоза дисковый гидравлический + дисковый гидравлический','FUJI','Nevada 2.0','mountain, frame aluminum, wheels 26 \", transmission 27 speed, hydraulic disc brakes + disc hydraulic'),(4,'LTD','Silent Night',1,'горный, женский, рама алюминий, колеса 26\", трансмиссия 21 скор., тормоза ободной механический + ободной механический','LTD','Silent Night','mountain, female, aluminum frame, 26 \"wheels, 21 speed transmission, mechanical brake + mechanical rim'),(5,'LTD','Miss 3.0',1,'горный, женский, рама алюминий, колеса 26\", трансмиссия 21 скор., тормоза ободной механический + ободной механический','LTD','Miss 3.0','mountain, female, aluminum frame, 26 \"wheels, 21 speed transmission, mechanical brake + mechanical rim'),(6,'LTD','Ventura 10',1,'горный, женский, рама хром-молибденовая сталь, колеса 26\", трансмиссия 18 скор., тормоза ободной механический + ободной механический','LTD','Ventura 10','mountain, female, chromium-molybdenum steel frame, 26 \"wheels, 18 speed transmission, brakes mechanical mechanical + mechanical rim'),(7,'Stels','Navigator 310 G',1,'городской, рама сталь Hi-ten, колеса 28\", вилка жесткая, трансмиссия 1 скор., тормоза отсутствует + ножной, вес 17.2 кг','Stels','Navigator 310 G','urban, Hi-ten steel frame, 28 \"wheels, fork stiff, transmission 1 speed, no brake + foot, weight 17.2 kg'),(8,'Aist','Pirate 1.0',2,'для мальчиков/для девочек, количество колес: 2, возраст: 6–9 лет, колеса 20\'\', рама: сталь Hi-ten','Aist','Pirate 1.0','for boys / girls, number of wheels: 2, age: 6-9 years, 20 \'\' wheels, frame: steel Hi-ten'),(9,'Stark','Tanuki Boy 16',2,'для мальчиков, количество колес: 2+2, возраст: 3–6 лет, колеса 16\'\', рама: сталь Hi-ten','Stark','Tanuki Boy 16','for boys, number of wheels: 2 + 2, age: 3-6 years, 16 \"wheels, frame: steel Hi-ten'),(10,'Aist','Rosy Junior 1.0',2,'горный, женский, подростковый, рама сталь Hi-ten, колеса 24\", вилка жесткая, трансмиссия 6 скор., тормоза ободной механический + ободной механический','Aist','Rosy Junior 1.0','mountain, female, teen, frame steel Hi-ten, 24 \"wheels, fork stiff, transmission 6 speed, brake rim mechanical + rim mechanical'),(11,'Aist','Rocky Junior 1.0',2,'горный, подростковый, рама сталь Hi-ten, колеса 24\", вилка жесткая, трансмиссия 6 скор., тормоза ободной механический + ободной механический','Aist','Rocky Junior 1.0','mountain, adolescent, Hi-ten steel frame, 24 \"wheels, rigid fork, 6-speed transmission, mechanical brake + mechanical rim'),(12,'Schwinn','Cruiser One Lady\'s',1,'круизер, рама сталь Hi-ten, колеса 26\", вилка жесткая, трансмиссия 1 скор., тормоза отсутствует + ножной, вес 16 кг','Schwinn','Cruiser One Lady\'s','cruiser, Hi-ten steel frame, 26 \"wheels, fork stiff, transmission 1 speed, no brake + foot, weight 16 kg'),(13,'Schwinn','Pixie Girl\'s',2,'для девочек, количество колес: 2+2, возраст: 2–4 лет, колеса 12\'\', рама: сталь Hi-ten','Schwinn','Pixie Girl\'s','for girls, number of wheels: 2 + 2, age: 2-4 years, 12 \"wheels, frame: steel Hi-ten'),(14,'Forward','Corsica 1.0',1,'городской, женский, рама алюминий, колеса 28\", вилка жесткая, трансмиссия 21 скор., тормоза ободной механический + ободной механический, вес 13.4 кг','Forward','Corsica 1.0','urban, female, aluminum frame, 28 \"wheels, fork rigid, transmission 21 speed, brakes mechanical mechanical + mechanical mechanical, weight 13.4 kg'),(15,'Format','5352',3,'тандем, рама алюминий, колеса 27.5\", вилка амортизационная с ходом 120 мм, трансмиссия 24 скор., тормоза дисковый механический + дисковый механический','Format','5352','tandem, aluminum frame, 27.5 \"wheels, shock absorber with a stroke of 120 mm, 24 speed transmission, disc mechanical disc brakes + disc mechanical'),(16,'Cannondale','Road Tandem 2',3,'тандем, рама алюминий, колеса 28\", вилка жесткая, трансмиссия 30 скор., тормоза дисковый механический + дисковый механический','Cannondale','Road Tandem 2','tandem, aluminum frame, 28 \"wheels, rigid fork, 30 speed transmission, disc mechanical disc brakes + disc mechanical');
/*!40000 ALTER TABLE `bicycle_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bicycle_types`
--

DROP TABLE IF EXISTS `bicycle_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bicycle_types` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name_ru` varchar(50) NOT NULL COMMENT 'Наименование типа велосипеда',
  `name_en` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Типы велосипедов (детский, взрослый, тандем и др.)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycle_types`
--

LOCK TABLES `bicycle_types` WRITE;
/*!40000 ALTER TABLE `bicycle_types` DISABLE KEYS */;
INSERT INTO `bicycle_types` VALUES (1,'Взрослый','Adult'),(2,'Детский','Children'),(3,'Тандем','Tandem');
/*!40000 ALTER TABLE `bicycle_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bicycles`
--

DROP TABLE IF EXISTS `bicycles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bicycles` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `rental_point_id` bigint(11) NOT NULL,
  `bicycle_model_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_RentalPoints_has_BicycleModels_BicycleModels1_idx` (`bicycle_model_id`),
  KEY `fk_RentalPoints_has_BicycleModels_RentalPoints1_idx` (`rental_point_id`),
  CONSTRAINT `fk_RentalPoints_has_BicycleModels_BicycleModels1` FOREIGN KEY (`bicycle_model_id`) REFERENCES `bicycle_models` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_RentalPoints_has_BicycleModels_RentalPoints1` FOREIGN KEY (`rental_point_id`) REFERENCES `rental_points` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='Велосипеды (одна и та же модель велосипеда может быть в нескольких пунктах проката)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycles`
--

LOCK TABLES `bicycles` WRITE;
/*!40000 ALTER TABLE `bicycles` DISABLE KEYS */;
INSERT INTO `bicycles` VALUES (1,1,4),(2,1,6),(3,5,1),(5,3,2),(6,2,5),(7,3,10),(8,5,5),(9,5,11),(10,4,4),(11,4,9),(12,4,9),(13,4,7),(14,2,8),(15,2,16),(16,2,2),(17,2,3),(18,3,3),(19,3,14),(20,3,4),(21,3,2),(24,5,13),(25,1,16),(26,3,3);
/*!40000 ALTER TABLE `bicycles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental_points`
--

DROP TABLE IF EXISTS `rental_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental_points` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `address_ru` varchar(255) NOT NULL COMMENT 'Адрес местоположения пунка проката',
  `phone` varchar(13) DEFAULT NULL COMMENT 'Телефон пунка проката',
  `working_hours_ru` varchar(100) DEFAULT NULL COMMENT 'Рабочие часы пунка проката',
  `address_en` varchar(255) NOT NULL,
  `working_hours_en` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Пункты проката';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_points`
--

LOCK TABLES `rental_points` WRITE;
/*!40000 ALTER TABLE `rental_points` DISABLE KEYS */;
INSERT INTO `rental_points` VALUES (1,'г. Минск, ул. Серова, д.20','+375291111111','с 09:00 до 18:00 (без выходных)','Minsk, Serova street, 20','from 09:00 to 18:00 (seven days a week)'),(2,'г. Минск, пр. Независимости, д. 120','+375291111112','с 09:00 до 18:00 (без выходных)','Minsk, Nesavisimosti avenue, 120','from 09:00 to 18:00 (seven days a week)'),(3,'г. Минск, ул. Я.Коласа, д. 55','+375291111113','с 09:00 до 18:00 (без выходных)','Minsk, Ya.Kolasa street, 55','from 09:00 to 18:00 (seven days a week)'),(4,'г. Минск, ул. Славинского, д. 67','+375291111114','с 09:00 до 19:00 (без выходных)','Minsk, Slavinskogo street, 67','from 09:00 to 18:00 (seven days a week)'),(5,'г. Минск, ул. Речная, д.89','+375291111115','с 09:00 до 20:00 (без выходных)','Minsk, Rechnaya street, 89','from 09:00 to 18:00 (seven days a week)');
/*!40000 ALTER TABLE `rental_points` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rents`
--

DROP TABLE IF EXISTS `rents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rents` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'Идентификатор',
  `datetime_create` datetime NOT NULL COMMENT 'Дата и время создания заказа на прокат',
  `datetime_finish` datetime DEFAULT NULL COMMENT 'Дата и время окончания проката (реальное)',
  `amount` decimal(5,2) DEFAULT NULL COMMENT 'Сумма проката',
  `user_id` bigint(11) NOT NULL COMMENT 'Пользователь',
  `bicycle_id` bigint(11) NOT NULL,
  `tariff_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Rents_Users1_idx` (`user_id`),
  KEY `fk_rents_bicycles1_idx` (`bicycle_id`),
  KEY `fk_rents_tariffs1_idx` (`tariff_id`),
  CONSTRAINT `fk_Rents_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rents_bicycles1` FOREIGN KEY (`bicycle_id`) REFERENCES `bicycles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rents_tariffs1` FOREIGN KEY (`tariff_id`) REFERENCES `tariffs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='Прокат';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rents`
--

LOCK TABLES `rents` WRITE;
/*!40000 ALTER TABLE `rents` DISABLE KEYS */;
INSERT INTO `rents` VALUES (1,'2017-07-01 13:00:00','2017-07-01 14:00:00',5.00,1,1,1),(2,'2017-07-01 11:00:00','2017-07-01 13:00:00',16.00,2,2,2),(3,'2017-08-01 09:00:00','2017-08-02 09:00:00',15.00,3,3,4),(4,'2017-08-01 09:00:00','2017-08-01 10:00:00',8.00,4,1,2),(5,'2017-08-02 09:00:00','2017-08-02 12:00:00',12.00,5,3,3),(6,'2017-07-01 12:00:00','2017-07-01 13:00:00',5.00,1,11,8),(7,'2017-07-01 11:00:00','2017-07-02 11:00:00',10.00,3,8,4),(8,'2017-12-12 11:00:00','2017-12-13 11:00:00',NULL,1,1,3),(9,'2017-12-12 11:00:00','2017-12-13 15:00:00',NULL,2,10,4),(10,'2017-12-12 11:00:00','2017-12-12 11:30:00',3.00,5,5,3),(11,'2017-07-01 11:00:00','2017-07-01 12:00:00',5.00,5,5,2),(12,'2017-07-01 11:00:00','2017-07-01 11:30:00',3.00,1,2,2),(13,'2017-09-01 10:00:00','2017-09-02 10:00:00',15.00,3,9,10),(14,'2017-09-01 10:00:00','2017-09-01 13:00:00',12.00,4,7,8),(15,'2017-09-15 10:00:00','2017-09-16 10:00:00',30.00,1,1,1),(16,'2018-01-05 08:17:32','2018-01-05 08:47:32',3.00,1,14,6),(17,'2018-01-05 08:20:45','2018-01-05 08:50:45',2.50,1,10,1),(18,'2018-01-05 08:21:09','2018-01-05 08:51:09',3.00,1,1,1),(19,'2018-01-05 08:21:21','2018-01-05 09:21:21',3.00,1,2,1),(20,'2018-01-05 10:55:28','2018-01-05 11:55:28',4.00,1,7,7),(21,'2018-01-06 11:23:53','2018-01-06 12:23:53',10.00,1,15,11),(22,'2018-01-07 11:52:01','2018-01-07 12:52:01',3.00,1,21,1),(23,'2018-01-07 17:43:36','2018-01-07 18:13:36',3.00,1,16,1),(24,'2018-01-16 04:35:05','2018-01-16 05:05:05',3.00,1,5,1),(25,'2018-01-25 14:15:56','2018-01-25 14:14:56',3.00,1,13,1),(26,'2018-01-25 14:16:27','2018-01-25 14:46:27',3.00,1,12,6),(27,'2018-01-25 14:16:47','2018-01-25 14:46:27',3.00,1,3,1),(28,'2018-01-25 14:16:55','2018-01-25 14:46:27',3.00,1,3,1),(29,'2018-01-28 17:21:42','2018-01-28 17:53:47',12.00,1,7,10),(30,'2018-01-28 17:55:17','2018-01-28 17:56:03',8.00,1,5,3),(31,'2018-01-28 17:56:57','2018-01-28 17:57:03',2.50,1,9,6),(32,'2018-01-28 17:58:57','2018-01-28 17:59:03',3.00,1,13,1),(33,'2018-01-28 17:59:27','2018-01-28 18:01:12',15.00,1,19,5),(34,'2018-01-28 18:01:47','2018-01-28 18:02:30',10.00,1,15,11),(35,'2018-01-29 07:34:48','2018-01-29 07:34:55',8.00,1,11,9),(36,'2018-01-30 17:55:58','2018-01-30 17:56:11',8.00,1,1,3),(37,'2018-01-30 19:05:50','2018-01-30 19:06:09',3.00,1,2,1),(38,'2018-01-31 19:27:24','2018-01-31 19:27:36',3.00,1,8,1),(39,'2018-01-31 19:28:21','2018-01-31 19:28:28',3.00,15,8,1),(40,'2018-01-31 19:33:51','2018-01-31 19:33:58',2.50,15,7,6);
/*!40000 ALTER TABLE `rents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT 'Наименование',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Роли (пользователь, администратор)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'user'),(2,'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariffs`
--

DROP TABLE IF EXISTS `tariffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariffs` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `rental_time` float NOT NULL COMMENT 'Время проката (в часах)',
  `price` decimal(5,2) NOT NULL COMMENT 'Стоимость проката',
  `bicycle_type_id` bigint(11) NOT NULL,
  `description_ru` varchar(255) DEFAULT NULL,
  `description_en` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Tariff_BicycleType_idx` (`bicycle_type_id`),
  CONSTRAINT `fk_Tariff_BicycleType` FOREIGN KEY (`bicycle_type_id`) REFERENCES `bicycle_types` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Стоимость проката в зависимости от типа велосипеда и времени аренды';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariffs`
--

LOCK TABLES `tariffs` WRITE;
/*!40000 ALTER TABLE `tariffs` DISABLE KEYS */;
INSERT INTO `tariffs` VALUES (1,0.5,3.00,1,'полчаса','half an hour'),(2,1,5.00,1,'1 час','1 hour'),(3,2,8.00,1,'2 часа','2 hours'),(4,3,12.00,1,'3 часа','3 hours'),(5,24,15.00,1,'сутки','day'),(6,0.5,2.50,2,'полчаса','half an hour'),(7,1,4.00,2,'1 час','1 hour'),(8,2,6.00,2,'2 часа','2 hours'),(9,3,8.00,2,'3 часа','3 hours'),(10,24,12.00,2,'сутки','day'),(11,1,10.00,3,'1 час','1 hour');
/*!40000 ALTER TABLE `tariffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL COMMENT 'Имя',
  `surname` varchar(45) NOT NULL COMMENT 'Фамилия',
  `patronymic` varchar(45) DEFAULT NULL COMMENT 'Отчество',
  `mobile_phone` varchar(13) NOT NULL COMMENT 'Мобильный телефон',
  `email` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL COMMENT 'Логин',
  `password` varchar(45) NOT NULL COMMENT 'Хеш-пароля (md5)',
  `last_enter_datetime` datetime DEFAULT NULL COMMENT 'Дата и время последнего входа в систему',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'статус пользователя (0 - активен, 1 - блокирован)',
  `block_datetime` datetime DEFAULT NULL COMMENT 'Дата и время блокировки',
  `balance` decimal(5,2) DEFAULT NULL COMMENT 'Баланс пользователя',
  `role_id` bigint(11) NOT NULL COMMENT 'Роль пользователя',
  `create_datetime` datetime DEFAULT NULL COMMENT 'Время и дата создания',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_Users_Roles1_idx` (`role_id`),
  CONSTRAINT `fk_Users_Roles1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='Пользователи';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Иван','Иванов','Иванович','+375291111111','test@test.by','test','d8578edf8458ce06fbc5bb76a58c5ca4','2017-07-11 11:00:00',0,NULL,27.50,1,NULL),(2,'Сергей','Сергеев','Сергеевич','+375291111115','test2@test.by','test2','d8578edf8458ce06fbc5bb76a58c5ca4','2016-03-11 11:00:00',0,'2016-03-12 11:00:00',0.00,1,NULL),(3,'Алексей','Алексеев','Алексеевич','+375291111113','test3@test.by','test3','d8578edf8458ce06fbc5bb76a58c5ca4','2016-03-11 11:00:00',0,NULL,0.00,1,NULL),(4,'Мария','Алексеева','Алексеевна','+375291111119','test4@test.by','test4','d8578edf8458ce06fbc5bb76a58c5ca4','2017-12-12 11:00:00',1,'2018-12-12 01:00:00',-100.00,1,NULL),(5,'Дарья','Иванова','Михайловна','+375291111120','test5@test.by','test5','d8578edf8458ce06fbc5bb76a58c5ca4','2017-11-11 11:00:00',0,NULL,50.00,1,NULL),(7,'Алексей','Вдадимиров','Владимирович','+375291111121','admin@test.by','admin','d8578edf8458ce06fbc5bb76a58c5ca4','2017-12-12 11:00:00',0,NULL,NULL,2,NULL),(8,'Пётр','Петров',NULL,'+375291111122',NULL,'admin2','d8578edf8458ce06fbc5bb76a58c5ca4','2017-12-12 11:00:00',0,NULL,NULL,2,NULL),(9,'Тимофей','Цикунов',NULL,'+375291111144',NULL,'admin3','d8578edf8458ce06fbc5bb76a58c5ca4','2017-12-01 11:00:00',0,NULL,NULL,2,NULL),(10,'Кристина','Александрова','Александровна','+375291111145',NULL,'admin4','d8578edf8458ce06fbc5bb76a58c5ca4','2016-07-01 11:00:00',1,'2016-07-02 11:00:00',NULL,2,NULL),(11,'Валерий','Светлов',NULL,'+375291111146',NULL,'admin5','d8578edf8458ce06fbc5bb76a58c5ca4','2016-01-12 11:00:00',0,NULL,NULL,2,NULL),(15,'Qwerty2018','Qwerty2018','Qwerty2018','1111111','Qwerty2018@ksd.cds','Qwerty2018','b535f80577691ac03c63b809fa52dc9a',NULL,0,NULL,-2.50,1,'2018-01-27 18:05:25');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-01  1:41:57
