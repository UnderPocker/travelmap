CREATE DATABASE  IF NOT EXISTS `travelmap` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `travelmap`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: travelmap
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo` (
  `place_id` varchar(30) DEFAULT NULL,
  `index` int DEFAULT NULL,
  `photo_url` varchar(130) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES ('70000001062478596',0,'https://i4.photo.2gis.com/main/branch/19/70000001062478596/common'),('70000001062478596',1,'https://i4.photo.2gis.com/main/geo/19/2674647933940375/view'),('2674540559794703',0,'https://i2.photo.2gis.com/main/branch/19/2674540559794703/common'),('2674540559732269',0,'https://i9.photo.2gis.com/main/branch/19/2674540559732269/common'),('70030076191382017',0,'https://avatars.mds.yandex.net/get-altay/9331640/2a00000188bae7c8f5d2366fd9456a9e2ceb/XXXL'),('2674751013129673',0,'https://i.ytimg.com/vi/i12lFbVliOo/maxresdefault.jpg'),('2674540559733834',0,'https://i6.photo.2gis.com/main/branch/19/2674540559733834/common'),('70000001056695382',0,'https://i5.photo.2gis.com/main/branch/19/70000001056695382/common'),('70000001056943169',0,'https://i4.photo.2gis.com/main/branch/19/70000001056943169/common'),('2674699496301977',0,'https://i2.photo.2gis.com/images/geo/0/30258560049519547_2346.jpg'),('70000001039646535',0,'https://i2.photo.2gis.com/main/branch/19/70000001039646535/common'),('2674699473518707',0,'https://core-pht-proxy.maps.yandex.ru/v1/photos/download?photo_id=1uw_ih7_cMRNuugiZky4wg&image_size=XXXL'),('2674699473518680',0,'https://avatars.mds.yandex.net/get-altay/10141118/2a0000018be414cc93541c8a8b5a61a24f7e/XXXL'),('70030076280680483',0,'https://avatars.mds.yandex.net/get-altay/5097807/2a00000182cb2ad5eb9376a6ef7da496e217/XXXL'),('2674540559866178',0,'https://i8.photo.2gis.com/main/branch/19/2674540559866178/common'),('2674540559750496',0,'https://i1.photo.2gis.com/main/branch/19/2674540559750496/common'),('2674699473518804',0,'https://i0.photo.2gis.com/images/geo/0/30258560060992915_c472.jpg'),('70030076190098810',0,'https://avatars.mds.yandex.net/get-altay/1811309/2a00000174d9d4e93c099a4fbf80a073504c/XXXL'),('2674540559750496',0,'https://avatars.mds.yandex.net/get-altay/9749133/2a0000018a498c4d15aefd3d62eb2595a92e/XXXL'),('70030076305054390',0,'https://avatars.mds.yandex.net/get-altay/1132477/2a00000188ddc849584f6207dddee733f179/XXXL'),('70030076360628075',0,'https://avatars.mds.yandex.net/get-altay/938969/2a0000018d5ae92db12bf245145ee0f06e79/XXXL'),('2674751013126213',0,'https://core-pht-proxy.maps.yandex.ru/v1/photos/download?photo_id=P_YrSv49Ru2aMtWyMIlrLA&image_size=X5L'),('2674751013126225',0,'https://core-pht-proxy.maps.yandex.ru/v1/photos/download?photo_id=qUA64hjWMsoJdgar8ypVew&image_size=X5L'),('2674751013126175',0,'https://happylove.top/uploads/posts/2023-09/1694328408_happylove-top-p-nizhnevolzhskaya-naberezhnaya-nizhnii-novg-7.jpg');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place` (
  `id` varchar(30) NOT NULL,
  `lon` decimal(12,8) DEFAULT NULL,
  `lat` decimal(12,8) DEFAULT NULL,
  `name` varchar(90) DEFAULT NULL,
  `general_type` int DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `double_gis_link` varchar(80) DEFAULT NULL,
  `work_time` varchar(20) DEFAULT NULL,
  `cost` varchar(7) DEFAULT NULL,
  `rating` decimal(3,2) DEFAULT NULL,
  `reviews` int DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `route_id` int DEFAULT NULL,
  `index` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `route_id` (`route_id`),
  CONSTRAINT `place_ibfk_1` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES ('2674540559732269',43.96124900,56.32835300,'Нижегородская ярмарка',2,'Организация выставок','Совнаркомовская улица, 13','https://2gis.ru/n_novgorod/firm/2674540559732269','09:00 - 13:00',NULL,4.40,426,'https://www.yarmarka.ru/','+7 (8312) 24‒88‒80',16,4),('2674540559733834',43.97462200,56.27187900,'Швейцария, парк культуры и отдыха',0,'Парки культуры и отдыха',NULL,'https://2gis.ru/n_novgorod/firm/2674540559733834','05:00 - 22:00',NULL,4.60,1238,NULL,NULL,17,0),('2674540559750496',44.00138400,56.32415700,'Театр драмы им. М. Горького',2,'Театры','Большая Покровская, 13','https://2gis.ru/n_novgorod/firm/2674540559750496','10:00 - 13:30',NULL,4.40,161,'https://www.drama.nnov.ru/','+7 (831) 419‒51‒73',19,4),('2674540559794703',43.97118800,56.33356900,'Кафедральный собор во имя святого благоверного князя Александра Невского, соборная трапеза',2,'Храмы / Соборы / Церкви','Стрелка, 3а','https://2gis.ru/n_novgorod/firm/2674540559794703','08:00 - 19:00',NULL,4.80,278,'https://nevskiy-nne.ru/','+7 (831) 246‒03‒31',16,3),('2674540559866178',43.99914400,56.32022200,'Волго-Вятское Главное управление Центрального банка РФ',2,'Интересные здания','Большая Покровская, 26','https://2gis.ru/n_novgorod/firm/2674540559866178','09:00 - 13:00',NULL,4.60,46,NULL,NULL,19,0),('2674699473518680',44.00946100,56.33066500,'Чкаловская лестница',2,NULL,NULL,'https://2gis.ru/n_novgorod/firm/2674699473518680',NULL,NULL,4.90,51,NULL,NULL,18,2),('2674699473518707',44.00670200,56.32657900,'Площадь Минина и Пожарского',2,NULL,NULL,'https://2gis.ru/n_novgorod/firm/2674699473518707',NULL,NULL,4.60,11,NULL,NULL,18,0),('2674699473518804',44.00110000,56.32169900,'Сквер имени Я.М. Свердлова',0,'Парки культуры и отдыха',NULL,'https://2gis.ru/n_novgorod/firm/2674699473518804',NULL,NULL,4.30,90,NULL,NULL,19,1),('2674699496301977',43.97181400,56.28385100,'Памятник природы регионального значения \"Урочище Слуда\"',2,'Природные достопримечательности',NULL,'https://2gis.ru/n_novgorod/firm/2674699496301977',NULL,NULL,5.00,6,NULL,NULL,17,3),('2674751013126175',43.99204500,56.33045000,'Нижне-Волжская набережная',0,NULL,NULL,'https://2gis.ru/n_novgorod/firm/2674751013126175',NULL,NULL,4.30,14,NULL,NULL,20,3),('2674751013126213',43.98529700,56.32525900,'набережная Федоровского',0,NULL,NULL,'https://2gis.ru/n_novgorod/firm/2674751013126213',NULL,NULL,4.50,26,NULL,NULL,20,1),('2674751013126225',43.98247900,56.32682300,'Рождественская',0,NULL,NULL,'https://2gis.ru/n_novgorod/firm/2674751013126225',NULL,NULL,5.00,4,NULL,NULL,20,2),('2674751013129673',43.94995000,56.34281000,'Волжская набережная',0,NULL,NULL,'https://2gis.ru/n_novgorod/firm/2674751013129673',NULL,NULL,5.00,1,NULL,NULL,16,0),('70000001039646535',44.00571300,56.32716000,'Нижегородский кремль, музей-заповедник',2,'Музеи','Кремль, 6а','https://2gis.ru/n_novgorod/firm/70000001039646535','10:00 - 19:00',NULL,4.80,998,NULL,NULL,18,1),('70000001056695382',43.97794800,56.28182900,'Планетарий 1',0,'Планетарий','парк Швейцария, Планетарий','https://2gis.ru/n_novgorod/firm/70000001056695382','10:00 - 22:00',NULL,4.40,127,NULL,NULL,17,1),('70000001056943169',43.97993100,56.28610100,'Океанис, торгово-развлекательный центр',0,'Торгово-развлекательные центры / Моллы','проспект Гагарина, 35 к1','https://2gis.ru/n_novgorod/firm/70000001056943169','10:00 - 22:00',NULL,3.90,595,NULL,NULL,17,2),('70000001062478596',43.97436500,56.33512200,'Пакгаузы, культурный центр',2,'Художественные выставки / Галереи','Стрелка, 21 лит Ж','https://2gis.ru/n_novgorod/firm/70000001062478596','24/7',NULL,4.90,77,'https://packhouses.strelkapark.ru/',NULL,16,2),('70030076190098810',44.00141800,56.32377000,'Сквер имени Е.А. Евстигнеева',0,'Парки культуры и отдыха',NULL,'https://2gis.ru/n_novgorod/firm/70030076190098810',NULL,NULL,4.90,27,NULL,NULL,19,3),('70030076191382017',43.97309400,56.33449300,'Стрелка',0,NULL,NULL,'https://2gis.ru/n_novgorod/firm/70030076191382017',NULL,NULL,4.10,7,NULL,NULL,16,1),('70030076280680483',44.01345800,56.33075500,'Александровский сад',0,'Парки культуры и отдыха',NULL,'https://2gis.ru/n_novgorod/firm/70030076280680483',NULL,NULL,4.70,157,NULL,NULL,18,3),('70030076305054390',44.00228100,56.32317800,'Фудкорт \"Street food market\"',1,NULL,NULL,'https://2gis.ru/n_novgorod/firm/70030076305054390',NULL,NULL,5.00,1,NULL,NULL,19,2),('70030076360628075',43.98270000,56.32499400,'Парк 800-летия Нижнего Новгорода',0,'Парки культуры и отдыха',NULL,'https://2gis.ru/n_novgorod/firm/70030076360628075',NULL,NULL,4.90,421,NULL,NULL,20,0);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (16,'Стрелка Оки и Волги','Исторические достопримечательности на слиянии главных рек города','https://static13.tgcnt.ru/posts/_0/ea/ea2bcf3b78f48e5bf00075d66f86daab.jpg'),(17,'Парк Швейцария','Крупнейший зеленый парк города','https://binomen.ru/photo/uploads/posts/2024-01/1706551384_binomen-ru-p-nizhnii-park-instagram-7.jpg'),(18,'Исторический центр','Главная достопримечательность города и ее окружение','https://sportishka.com/uploads/posts/2022-04/1650496686_19-sportishka-com-p-glavnaya-dostoprimechatelnost-nizhnego-nov-20.jpg'),(19,'Большая Покровская улица','Главная пешеходная улица и ее достопримечательности','https://opennov.ru/sites/default/files/images/news/c533d63f6bce248c1479828496d68366.jpeg'),(20,'Панорамы города','Лучшие виды на столицу закатов','https://xcourse.me/images/showplaces/464/a1045885083e1220376973af9f7670da.jpg');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-23 16:15:17
