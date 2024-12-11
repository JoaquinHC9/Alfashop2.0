-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: alfashop
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Electrónicos','Productos de tecnología como teléfonos, computadoras, etc.'),(2,'Comida','Alimentos y bebidas de todo tipo'),(3,'Accesorios','Complementos y accesorios para diferentes productos');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `monto` decimal(32,8) NOT NULL,
  `fecha_creacion` timestamp NOT NULL,
  `metodo_pago` varchar(255) NOT NULL,
  `id_pedido` int DEFAULT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_pedido` (`id_pedido`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES (1,714.98000000,'2024-09-30 03:00:54','PAYPAL',1),(2,14.98000000,'2024-09-30 03:00:54','VISA',2),(3,1729.94000000,'2024-11-23 15:18:29','CREDIT_CARD',9),(4,1729.94000000,'2024-11-29 02:30:33','CREDIT_CARD',10),(5,1729.94000000,'2024-11-29 02:30:41','CREDIT_CARD',11),(6,1729.94000000,'2024-11-29 02:32:09','CREDIT_CARD',12),(7,1729.94000000,'2024-11-29 02:32:24','CREDIT_CARD',13),(8,1729.94000000,'2024-11-29 02:32:40','CREDIT_CARD',14),(9,1729.94000000,'2024-11-29 02:32:40','CREDIT_CARD',15),(11,1729.94000000,'2024-11-29 02:32:40','CREDIT_CARD',17),(13,1729.94000000,'2024-11-29 02:32:41','CREDIT_CARD',19),(14,1729.94000000,'2024-11-29 02:32:42','CREDIT_CARD',20),(15,1729.94000000,'2024-11-29 02:32:42','CREDIT_CARD',21),(16,1729.94000000,'2024-11-29 02:32:42','CREDIT_CARD',22),(17,1729.94000000,'2024-11-29 02:32:42','CREDIT_CARD',23),(18,1729.94000000,'2024-11-29 02:32:43','CREDIT_CARD',24),(19,1729.94000000,'2024-11-29 02:32:43','CREDIT_CARD',25),(20,1729.94000000,'2024-11-29 02:32:43','CREDIT_CARD',26),(21,1729.94000000,'2024-11-29 02:32:43','CREDIT_CARD',27),(22,1729.94000000,'2024-11-29 02:32:44','CREDIT_CARD',28),(23,1729.94000000,'2024-11-29 02:32:44','CREDIT_CARD',29),(25,1729.94000000,'2024-11-29 02:32:44','CREDIT_CARD',31),(26,1729.94000000,'2024-11-29 02:32:44','CREDIT_CARD',32),(27,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',33),(28,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',34),(29,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',35),(30,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',36),(31,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',37),(32,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',38),(33,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',39),(34,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',40),(35,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',41),(36,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',42),(37,1729.94000000,'2024-11-29 02:32:45','CREDIT_CARD',43),(38,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',44),(39,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',45),(40,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',46),(41,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',47),(42,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',48),(43,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',49),(44,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',50),(45,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',51),(46,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',52),(48,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',54),(49,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',55),(50,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',56),(51,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',57),(52,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',58),(53,1729.94000000,'2024-11-29 02:32:46','CREDIT_CARD',59),(54,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',60),(55,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',61),(56,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',62),(57,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',63),(58,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',64),(59,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',65),(60,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',66),(61,1729.94000000,'2024-11-29 02:32:47','CREDIT_CARD',67),(62,191.95000000,'2024-12-08 23:31:42','BITCOIN',1014);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `fecha_creacion` timestamp NOT NULL,
  `metodo_pago` varchar(255) NOT NULL,
  `total_monto` decimal(38,2) NOT NULL,
  `id_usuario` int DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2024-09-30 03:00:54','PAYPAL',714.98,1,'Completado'),(2,'2024-09-30 03:00:54','VISA',14.98,2,'Completado'),(3,'2024-11-21 21:50:18','CREDIT_CARD',1729.94,1,'PENDIENTE'),(4,'2024-11-22 00:40:32','PAYPAL',158.98,3,'APROBADO'),(5,'2024-11-22 00:42:30','PAYPAL',1399.98,3,'APROBADO'),(6,'2024-11-22 00:44:16','PAYPAL',299.98,3,'APROBADO'),(7,'2024-11-22 00:47:59','PAYPAL',3599.97,3,'APROBADO'),(9,'2024-11-23 15:18:29','CREDIT_CARD',1729.94,1,'APROBADO'),(10,'2024-11-29 02:30:33','CREDIT_CARD',1729.94,1,'APROBADO'),(11,'2024-11-29 02:30:41','CREDIT_CARD',1729.94,22,'APROBADO'),(12,'2024-11-29 02:32:09','CREDIT_CARD',1729.94,22,'APROBADO'),(13,'2024-11-29 02:32:24','CREDIT_CARD',1729.94,22,'APROBADO'),(14,'2024-11-29 02:32:40','CREDIT_CARD',1729.94,22,'APROBADO'),(15,'2024-11-29 02:32:40','CREDIT_CARD',1729.94,22,'APROBADO'),(17,'2024-11-29 02:32:40','CREDIT_CARD',1729.94,22,'APROBADO'),(19,'2024-11-29 02:32:41','CREDIT_CARD',1729.94,22,'APROBADO'),(20,'2024-11-29 02:32:42','CREDIT_CARD',1729.94,22,'APROBADO'),(21,'2024-11-29 02:32:42','CREDIT_CARD',1729.94,22,'APROBADO'),(22,'2024-11-29 02:32:42','CREDIT_CARD',1729.94,22,'APROBADO'),(23,'2024-11-29 02:32:42','CREDIT_CARD',1729.94,22,'APROBADO'),(24,'2024-11-29 02:32:42','CREDIT_CARD',1729.94,22,'APROBADO'),(25,'2024-11-29 02:32:43','CREDIT_CARD',1729.94,22,'APROBADO'),(26,'2024-11-29 02:32:43','CREDIT_CARD',1729.94,22,'APROBADO'),(27,'2024-11-29 02:32:43','CREDIT_CARD',1729.94,22,'APROBADO'),(28,'2024-11-29 02:32:44','CREDIT_CARD',1729.94,22,'APROBADO'),(29,'2024-11-29 02:32:44','CREDIT_CARD',1729.94,22,'APROBADO'),(31,'2024-11-29 02:32:44','CREDIT_CARD',1729.94,22,'APROBADO'),(32,'2024-11-29 02:32:44','CREDIT_CARD',1729.94,22,'APROBADO'),(33,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(34,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(35,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(36,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(37,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(38,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(39,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(40,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(41,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(42,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(43,'2024-11-29 02:32:45','CREDIT_CARD',1729.94,22,'APROBADO'),(44,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(45,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(46,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(47,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(48,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(49,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(50,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(51,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(52,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(54,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(55,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(56,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(57,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(58,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(59,'2024-11-29 02:32:46','CREDIT_CARD',1729.94,22,'APROBADO'),(60,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(61,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(62,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(63,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(64,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(65,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(66,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(67,'2024-11-29 02:32:47','CREDIT_CARD',1729.94,22,'APROBADO'),(1014,'2024-12-08 23:31:42','BITCOIN',191.95,3,'APROBADO');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_producto`
--

DROP TABLE IF EXISTS `pedido_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cantidad` double NOT NULL,
  `precio_total` decimal(38,2) NOT NULL,
  `id_pedido` int DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pedido` (`id_pedido`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `pedido_producto_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
  CONSTRAINT `pedido_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_producto`
--

LOCK TABLES `pedido_producto` WRITE;
/*!40000 ALTER TABLE `pedido_producto` DISABLE KEYS */;
INSERT INTO `pedido_producto` VALUES (1,1,699.99,1,1),(2,1,149.99,1,3),(3,1,8.99,2,4),(4,1,5.99,2,5),(5,2,1399.98,3,1),(6,1,149.99,3,3),(7,3,179.97,3,6),(8,1,149.99,4,3),(9,1,8.99,4,4),(10,2,1399.98,5,1),(11,2,299.98,6,3),(12,3,3599.97,7,2),(16,2,1399.98,9,1),(17,1,149.99,9,3),(18,3,179.97,9,6),(19,2,1399.98,10,1),(20,1,149.99,10,3),(21,3,179.97,10,6),(22,2,1399.98,11,1),(23,1,149.99,11,3),(24,3,179.97,11,6),(25,2,1399.98,12,1),(26,1,149.99,12,3),(27,3,179.97,12,6),(28,2,1399.98,13,1),(29,1,149.99,13,3),(30,3,179.97,13,6),(31,2,1399.98,14,1),(32,1,149.99,14,3),(33,3,179.97,14,6),(34,2,1399.98,15,1),(37,1,149.99,15,3),(38,3,179.97,15,6),(40,2,1399.98,17,1),(41,1,149.99,17,3),(42,3,179.97,17,6),(46,2,1399.98,19,1),(47,1,149.99,19,3),(48,3,179.97,19,6),(49,2,1399.98,20,1),(50,1,149.99,20,3),(51,3,179.97,20,6),(52,2,1399.98,21,1),(53,1,149.99,21,3),(54,3,179.97,21,6),(55,2,1399.98,22,1),(56,1,149.99,22,3),(57,3,179.97,22,6),(58,2,1399.98,23,1),(59,1,149.99,23,3),(60,3,179.97,23,6),(61,2,1399.98,24,1),(62,1,149.99,24,3),(63,3,179.97,24,6),(64,2,1399.98,25,1),(65,1,149.99,25,3),(66,3,179.97,25,6),(67,2,1399.98,26,1),(68,1,149.99,26,3),(69,3,179.97,26,6),(70,2,1399.98,27,1),(71,1,149.99,27,3),(72,3,179.97,27,6),(73,2,1399.98,28,1),(74,1,149.99,28,3),(75,3,179.97,28,6),(76,2,1399.98,29,1),(77,1,149.99,29,3),(78,3,179.97,29,6),(82,2,1399.98,31,1),(83,1,149.99,31,3),(84,3,179.97,31,6),(85,2,1399.98,32,1),(86,1,149.99,32,3),(87,3,179.97,32,6),(88,2,1399.98,33,1),(89,1,149.99,33,3),(90,3,179.97,33,6),(91,2,1399.98,34,1),(92,1,149.99,34,3),(93,3,179.97,34,6),(94,2,1399.98,35,1),(95,1,149.99,35,3),(96,3,179.97,35,6),(97,2,1399.98,36,1),(98,1,149.99,36,3),(99,3,179.97,36,6),(100,2,1399.98,37,1),(101,1,149.99,37,3),(102,3,179.97,37,6),(103,2,1399.98,38,1),(104,1,149.99,38,3),(105,3,179.97,38,6),(106,2,1399.98,39,1),(107,1,149.99,39,3),(108,3,179.97,39,6),(109,2,1399.98,40,1),(110,1,149.99,40,3),(111,3,179.97,40,6),(112,2,1399.98,41,1),(113,1,149.99,41,3),(114,3,179.97,41,6),(115,2,1399.98,42,1),(116,1,149.99,42,3),(117,3,179.97,42,6),(118,2,1399.98,43,1),(119,1,149.99,43,3),(120,3,179.97,43,6),(121,2,1399.98,44,1),(122,1,149.99,44,3),(123,3,179.97,44,6),(124,2,1399.98,45,1),(125,1,149.99,45,3),(126,3,179.97,45,6),(127,2,1399.98,46,1),(128,1,149.99,46,3),(129,3,179.97,46,6),(130,2,1399.98,47,1),(131,1,149.99,47,3),(132,3,179.97,47,6),(133,2,1399.98,48,1),(134,1,149.99,48,3),(135,3,179.97,48,6),(136,2,1399.98,49,1),(137,1,149.99,49,3),(138,3,179.97,49,6),(139,2,1399.98,50,1),(140,1,149.99,50,3),(141,3,179.97,50,6),(142,2,1399.98,51,1),(143,1,149.99,51,3),(144,3,179.97,51,6),(145,2,1399.98,52,1),(146,1,149.99,52,3),(147,3,179.97,52,6),(151,2,1399.98,54,1),(152,1,149.99,54,3),(153,3,179.97,54,6),(154,2,1399.98,55,1),(155,1,149.99,55,3),(156,3,179.97,55,6),(157,2,1399.98,56,1),(158,1,149.99,56,3),(159,3,179.97,56,6),(160,2,1399.98,57,1),(161,1,149.99,57,3),(162,3,179.97,57,6),(163,2,1399.98,58,1),(164,1,149.99,58,3),(165,3,179.97,58,6),(166,2,1399.98,59,1),(167,1,149.99,59,3),(168,3,179.97,59,6),(169,2,1399.98,60,1),(170,1,149.99,60,3),(171,3,179.97,60,6),(172,2,1399.98,61,1),(173,1,149.99,61,3),(174,3,179.97,61,6),(175,2,1399.98,62,1),(176,1,149.99,62,3),(177,3,179.97,62,6),(178,2,1399.98,63,1),(179,1,149.99,63,3),(180,3,179.97,63,6),(181,2,1399.98,64,1),(182,1,149.99,64,3),(183,3,179.97,64,6),(184,2,1399.98,65,1),(185,1,149.99,65,3),(186,3,179.97,65,6),(187,2,1399.98,66,1),(188,1,149.99,66,3),(189,3,179.97,66,6),(190,2,1399.98,67,1),(191,1,149.99,67,3),(192,3,179.97,67,6),(193,2,11.98,1014,5),(194,3,179.97,1014,6);
/*!40000 ALTER TABLE `pedido_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `stock` int DEFAULT '0',
  `precio` decimal(32,8) DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `producto_ibfk_1` (`id_categoria`),
  KEY `idx_nombre_producto` (`nombre`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Smartphone','Teléfono inteligente con 128GB de almacenamiento',150,699.99000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/28289272-1000-1000/imageUrl_1.jpg'),(2,'Laptop','Computadora portátil con procesador Intel i7',150,1199.99000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30051003-1000-1000/20424444.jpg'),(3,'Audífonos Bluetooth','Auriculares inalámbricos con cancelación de ruido',150,149.99000000,3,'https://imagedelivery.net/4fYuQyy-r8_rpBpcY7lH_A/falabellaPE/18483005_1/w=1500,h=1500,fit=pad'),(4,'Pizza Margherita','Pizza clásica con tomate, mozzarella y albahaca',150,8.99000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/8983396-1000-1000/20201834.jpg'),(5,'Hamburguesa Doble','Hamburguesa con doble carne y queso',118,5.99000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/2446512-1000-1000/114480.jpg'),(6,'Mouse Gamer','Ratón inalámbrico para juegos con luces LED',147,59.99000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/19540448-1000-1000/imageUrl_2.jpg'),(7,'Tomate Italiano','Un tomate de textura firme y sabor dulce, ideal para ensaladas y salsas. Su color rojo vibrante lo hace perfecto para realzar cualquier plato.',99,5.60000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/518590-220-220/60769.jpg?v=637417681802200000'),(8,'Plátano de Seda','Un plátano suave y dulce, con una textura cremosa. Perfecto para postres o como acompañante de batidos y jugos.',99,2.89000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/29450552-220-220/772631.jpg?v=638616384640530000'),(9,'Palta Fuerte','Aguacate cremoso con un sabor suave y una textura ideal para hacer guacamole o agregar a ensaladas frescas.',99,9.90000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/20372457-220-220/64576.jpg?v=638023231236300000'),(10,'Papaya','Fruta tropical de pulpa suave y dulce, rica en vitaminas y antioxidantes. Perfecta para comer sola o como ingrediente en ensaladas de frutas.',99,3.49000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/182396-220-220/924003.jpg?v=636117143029130000'),(11,'Cebolla Roja P x kg','Cebolla de sabor suave y dulce, ideal para ensaladas, guisos o como aderezo para carnes.',99,2.09000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/518589-220-220/844123.jpg?v=637417681771730000'),(12,'Naranja para Jugo','Naranjas jugosas y frescas, perfectas para preparar jugos naturales y refrescantes.',99,2.39000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/10427282-220-220/48640.jpg?v=637865923151370000'),(13,'Limón Ácido','Limón fresco, ácido y lleno de vitamina C. Perfecto para dar sabor a tus comidas o para preparar bebidas refrescantes.',99,3.69000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/177076-220-220/60288.jpg?v=635864715655970000'),(14,'Papa Blanca','Papa de textura suave y sabor neutro, ideal para hacer puré, papas fritas o como acompañante en cualquier comida.',99,2.99000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/518585-220-220/848879.jpg?v=637417681641900000'),(15,'Zanahoria','Zanahorias frescas y crujientes, perfectas para ensaladas, sopas o para comer como snack saludable.',99,1.68000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/226526-220-220/120930.jpg?v=636977898066530000'),(16,'Papa Amarilla Tumbay','Papa amarilla de sabor suave y dulce, ideal para hacer puré o acompañar carnes.',99,6.79000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/518600-220-220/20171835.jpg?v=637417682122930000'),(17,'Zapallo Macre','Zapallo con pulpa suave y un sabor ligeramente dulce, perfecto para sopas y guisos.',99,1.99000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/178669-220-220/20017639.jpg?v=635909118247730000'),(18,'Sandía','Fruta refrescante y jugosa, ideal para el verano. Perfecta para disfrutar sola o en ensaladas de frutas.',99,1.50000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/214684-220-220/65702.jpg?v=636905883114370000'),(19,'Mandarina Malvacea','Mandarinas dulces y fáciles de pelar, perfectas para un snack rápido o para hacer jugos naturales.',99,5.70000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/518575-220-220/874847.jpg?v=637417681323630000'),(20,'Vainita Americana','Judías verdes frescas, de sabor suave y ligeramente dulce, ideales para guisos, ensaladas o como acompañante de carnes.',99,6.49000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/177992-220-220/60844.jpg?v=635909106219100000'),(21,'Ají Amarillo x kg','Ají picante y aromático, ideal para dar sabor a las salsas y platos típicos de la gastronomía peruana.',99,5.49000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/177084-220-220/58797.jpg?v=635864715735700000'),(22,'Lechuga Americana BELL\'s Bolsa 300g','Lechuga fresca y crujiente, ideal para ensaladas, wraps o como acompañante de otros platos.',99,1.69000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/266531-220-220/29529.jpg?v=637093725173230000'),(23,'Choclo Entero un','Mazorca de maíz dulce, perfecta para hervir o asar, ideal para acompañar carnes a la parrilla o como parte de una ensalada.',99,1.89000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/173637-220-220/993908.jpg?v=635785255623570000'),(24,'Camote Amarillo Procesado','Camote amarillo, naturalmente dulce y lleno de nutrientes. Perfecto para preparar puré o como ingrediente en sopas y guisos.',99,2.79000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/226523-220-220/65107.jpg?v=636977898045430000'),(25,'Espinaca BELL\'S Bolsa 350g','Espinaca fresca, ideal para ensaladas, batidos o como base de sopas y guisos.',99,3.99000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/248702-220-220/66278.jpg?v=637062380101670000'),(26,'Pimiento Morrón','Pimiento morrón fresco y jugoso, ideal para ensaladas, guisos, o para dar color a cualquier plato.',99,6.29000000,2,'https://plazavea.vteximg.com.br/arquivos/ids/226561-220-220/120941.jpg?v=636978593891600000'),(47,'Smartphone HONOR HONOR X5B 6.56\" 4GB 64GB 50 MP + 0.08 MP Azul','Smartphone HONOR X5B 6.56\" 4GB 64GB 50 MP + 0.08 MP Azul, ideal para quienes buscan un teléfono con una cámara potente, una pantalla amplia de 6.56\", y un diseño elegante, todo a un excelente precio.',99,329.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30021990-220-220/20463749.jpg?v=638663915543730000'),(48,'Televisor SAMSUNG CRYSTAL UHD 65\" UHD 4K Smart TV UN65DU7000GXPE','Televisor SAMSUNG CRYSTAL UHD 65\" UHD 4K Smart TV UN65DU7000GXPE, un televisor de 65\" con resolución 4K, diseño elegante y funcionalidades smart, perfecto para los amantes de la tecnología que buscan una experiencia visual de calidad.',99,1699.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29428332-220-220/20425208.jpg?v=638609473015500000'),(49,'Televisor BLACKLINE LED 32\" HD Smart TV BL32-T3000HD','Televisor BLACKLINE LED 32\" HD Smart TV BL32-T3000HD, un televisor compacto de 32\", ideal para espacios más pequeños, con tecnología Smart TV para disfrutar de tus contenidos favoritos.',99,429.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30296277-220-220/20422735.jpg?v=638691552638800000'),(50,'Televisor SAMSUNG CRYSTAL UHD 50\" UHD 4K Smart TV UN50DU7000GXPE','Televisor SAMSUNG CRYSTAL UHD 50\" UHD 4K Smart TV UN50DU7000GXPE, una opción versátil con resolución 4K para una calidad de imagen espectacular, perfecto para disfrutar de películas y programas en alta definición.',99,1299.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30249477-220-220/20425221.jpg?v=638682051755970000'),(51,'Televisor HISENSE LED 58\" UHD 4K Smart TV 58A6N','Televisor HISENSE LED 58\" UHD 4K Smart TV 58A6N, ofrece una gran pantalla de 58\" con resolución 4K, ideal para aquellos que buscan una experiencia cinematográfica en casa.',99,1099.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29434277-220-220/20424505.jpg?v=638611204176670000'),(52,'Televisor TCL LED 50\" UHD 4K Smart TV 50P755','Televisor TCL LED 50\" UHD 4K Smart TV 50P755, un televisor con pantalla de 50\" y resolución 4K, para quienes desean una excelente calidad de imagen en su hogar.',99,1099.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29258604-220-220/20416783.jpg?v=638572577884300000'),(53,'Consola PS5 HW 2015 STD Latam Bndl 1 RTRN RC','Consola PS5 HW 2015 STD Latam Bndl 1 RTRN RC, la última consola de videojuegos de Sony, con un potente rendimiento gráfico, compatible con los mejores títulos de la nueva generación.',99,2799.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29033795-220-220/20404194.jpg?v=638501483609400000'),(54,'Smartphone SAMSUNG Galaxy A25 6.5\" 8GB 256GB 50MP + 8MP + 2MP Black','Smartphone SAMSUNG Galaxy A25 6.5\" 8GB 256GB 50MP + 8MP + 2MP Black, un smartphone con una pantalla de 6.5\", gran capacidad de memoria y un sistema de cámaras versátil para capturar todos tus momentos.',99,899.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29450946-220-220/20394554.jpg?v=638616392674770000'),(55,'Smartphone HONOR X6B 6.55\" 6GB 256GB 50MP+2MP Ocean Cyan','Smartphone HONOR X6B 6.55\" 6GB 256GB 50MP+2MP Ocean Cyan, un teléfono inteligente con pantalla de 6.55\", ideal para quienes buscan una gran capacidad de almacenamiento y cámaras de alta calidad.',99,589.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30021748-220-220/20429868.jpg?v=638663911826400000'),(56,'Smartphone SAMSUNG Galaxy A06 6.7\" 4GB 64GB 50MP + 2MP Negro','Smartphone SAMSUNG Galaxy A06 6.7\" 4GB 64GB 50MP + 2MP Negro, un smartphone con una gran pantalla de 6.7\" y cámaras de alta resolución, ideal para quienes buscan un dispositivo equilibrado y de calidad.',99,399.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29686690-220-220/20427280.jpg?v=638647824520900000'),(57,'Televisor SAMSUNG QLED 50\" UHD 4K Smart TV QN50Q60DAGXPE','Televisor SAMSUNG QLED 50\" UHD 4K Smart TV QN50Q60DAGXPE, televisor con tecnología QLED que ofrece una excelente calidad de imagen y una experiencia Smart TV completa.',99,1499.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29451095-220-220/20425216.jpg?v=638616394455200000'),(58,'Televisor SAMSUNG CRYSTAL UHD 65\" UHD 4K Smart TV UN65DU8000GXPE','Televisor SAMSUNG CRYSTAL UHD 65\" UHD 4K Smart TV UN65DU8000GXPE, una opción perfecta con una pantalla grande de 65\" y resolución 4K para los que buscan calidad y entretenimiento.',99,2299.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29307432-220-220/20422310.jpg?v=638584887317370000'),(59,'Smartphone ZTE A54 6.6\" 4GB 128GB 13MP Gris','Smartphone ZTE A54 6.6\" 4GB 128GB 13MP Gris, teléfono con una pantalla de 6.6\", ideal para quienes buscan un equipo económico pero con buenas características de cámara y rendimiento.',99,289.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/28793033-220-220/20395213.jpg?v=638447905384000000'),(60,'Smartphone SAMSUNG Galaxy A15 6.5\" 8GB 256GB 50MP + 5MP + 2MP Blue Black','Smartphone SAMSUNG Galaxy A15 6.5\" 8GB 256GB 50MP + 5MP + 2MP Blue Black, un dispositivo de 6.5\" con una cámara potente y gran capacidad de almacenamiento, ideal para quienes buscan un equilibrio entre rendimiento y diseño.',99,599.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29450972-220-220/20394555.jpg?v=638616392935270000'),(61,'Televisor TCL LED 43\" UHD 4K Smart Tv 43P635','Televisor TCL LED 43\" UHD 4K Smart Tv 43P635, un televisor compacto pero con todas las funcionalidades de Smart TV y una excelente resolución 4K para disfrutar del mejor entretenimiento.',99,849.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/27596512-220-220/20377868.jpg?v=638320037606470000'),(62,'Smartphone HONOR X6B 6.55\" 4GB 128GB 50MP+2MP Forest Green','Smartphone HONOR X6B 6.55\" 4GB 128GB 50MP+2MP Forest Green, dispositivo con cámara de 50 MP, pantalla de 6.55\" y gran capacidad de almacenamiento, ideal para quienes buscan un smartphone funcional y atractivo.',99,479.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30021763-220-220/20462401.jpg?v=638663912075100000'),(63,'Smartphone HONOR X6B 6.55\" 6GB 256GB 50MP+2MP Starry Purple','Smartphone HONOR X6B 6.55\" 6GB 256GB 50MP+2MP Starry Purple, smartphone con características premium, pantalla amplia, cámaras de alta resolución y gran capacidad de almacenamiento.',99,589.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30021743-220-220/20429871.jpg?v=638663911722300000'),(64,'Smartphone ZTE Blade V60 Smart 6.6\" 4GB 256GB 50MP Negro','Smartphone ZTE Blade V60 Smart 6.6\" 4GB 256GB 50MP Negro, un dispositivo económico pero con características de gama media, ideal para quienes buscan un buen rendimiento y cámara de 50 MP.',99,379.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29307806-220-220/20426031.jpg?v=638585077706430000'),(65,'Televisor LG LED 65\" UHD 4K ThinQ AI 65UT8050PSB','Televisor LG LED 65\" UHD 4K ThinQ AI 65UT8050PSB, televisor con pantalla grande de 65\" y tecnología avanzada ThinQ AI para una experiencia Smart TV única.',99,2399.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/30284682-220-220/20424543.jpg?v=638688321135630000'),(66,'Tablet XIAOMI Redmi Pad SE 11\" 4GB 128GB Gris','Tablet XIAOMI Redmi Pad SE 11\" 4GB 128GB Gris, tablet con pantalla de 11\", ideal para quienes buscan una pantalla amplia y un buen rendimiento para entretenimiento y productividad.',99,599.00000000,1,'https://plazavea.vteximg.com.br/arquivos/ids/29227410-220-220/20423245.jpg?v=638567138970670000'),(87,'Parlante Amazon Alexa Echo Dot 5ta Generación Smart Hub Azul','Parlante Amazon Alexa Echo Dot 5ta Generación Smart Hub Azul, un dispositivo compacto con Alexa integrado, ideal para controlar tu hogar inteligente, escuchar música y mucho más.',99,179.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/28478570-220-220/image-7a2bde4f265643aab00e0d0b585bdba1.jpg?v=638369772712200000'),(88,'Audífonos On Ear con Bluetooth SONY WH-CH520 Negro','Audífonos On Ear con Bluetooth SONY WH-CH520 Negro, con sonido claro y conexión inalámbrica, perfectos para disfrutar de tu música y hacer llamadas con comodidad.',99,119.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/27474572-220-220/20356221.jpg?v=638295846791270000'),(89,'Audífonos In Ear SKULLCANDY Tw Rail Anc Black','Audífonos In Ear SKULLCANDY Tw Rail Anc Black, auriculares con cancelación activa de ruido para una experiencia auditiva envolvente y sin distracciones.',99,229.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/29227420-220-220/20423880.jpg?v=638567139092730000'),(90,'Audífonos On Ear con Bluetooth SONY WH-CH520 Azul','Audífonos On Ear con Bluetooth SONY WH-CH520 Azul, diseño ergonómico y sonido nítido, ideales para largas horas de escucha sin cables.',99,119.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/27474550-220-220/20356219.jpg?v=638295846565500000'),(91,'Torre de sonido LG XBOOM RNC9 Multi-Bluetooth Karaoke Star','Torre de sonido LG XBOOM RNC9 Multi-Bluetooth Karaoke Star, con tecnología Bluetooth y modo karaoke, ideal para fiestas y eventos.',99,1249.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/29308554-220-220/20353607.jpg?v=638586345348600000'),(92,'Parlante JBL PartyBox Stage 320 Bluetooth IPX4','Parlante JBL PartyBox Stage 320 Bluetooth IPX4, parlante de alto rendimiento con sonido potente y resistente al agua, perfecto para fiestas al aire libre.',99,2199.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/29057332-220-220/imageUrl_1.jpg?v=638512106360370000'),(93,'Audífonos Samsung Galaxy Buds FE In Ear Bluetooth Negro','Audífonos Samsung Galaxy Buds FE In Ear Bluetooth Negro, con sonido nítido y conectividad Bluetooth para un audio inalámbrico de alta calidad.',99,269.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/28592942-220-220/image-a36181cbb79d49188436f1cbfd1d7068.jpg?v=638388840800430000'),(94,'Audífonos Inalámbricos Xiaomi Redmi Buds 5 Midnight Black','Audífonos Inalámbricos Xiaomi Redmi Buds 5 Midnight Black, con sonido equilibrado y conexión rápida, ideales para el día a día.',99,155.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/28354105-220-220/image-c6cabcb8f6b04dc4be26ebabd6b777f9.jpg?v=638361113749800000'),(95,'Parlante Portátil MICRONICS Evers EV1000 Negro','Parlante Portátil MICRONICS Evers EV1000 Negro, parlante compacto con buen sonido y batería de larga duración, ideal para llevar a todas partes.',99,69.90000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/27580840-220-220/20376376.jpg?v=638316366853730000'),(96,'Soundbar SAMSUNG Bluetooth 2.0Ch HW-C400/PE','Soundbar SAMSUNG Bluetooth 2.0Ch HW-C400/PE, barra de sonido con tecnología Bluetooth para una experiencia auditiva envolvente desde tu televisor.',99,299.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/28495876-220-220/20355522.jpg?v=638371606558400000'),(97,'Audifonos Bluetooth Lenovo HD200 más Mochila Portalaptop Regalo','Audifonos Bluetooth Lenovo HD200 más Mochila Portalaptop Regalo, auriculares cómodos y Bluetooth de calidad, acompañados de una mochila práctica para tu laptop.',99,79.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/26926959-220-220/image-ee7f9b7c93ec4a61ae723a747e23dd96.jpg?v=638235251003930000'),(98,'Audífonos Inalámbricos In-Ear HUAWEI FreeBuds 5i Azul','Audífonos Inalámbricos In-Ear HUAWEI FreeBuds 5i Azul, con diseño ergonómico y sonido de alta calidad, ideales para quienes buscan comodidad y estilo.',99,159.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/26535656-220-220/image-9daffbc7c22148a29797535cc849bcc8.jpg?v=638217699599930000'),(99,'Torre de sonido LG XBOOM RNC5 Multi-Bluetooth Karaoke Star','Torre de sonido LG XBOOM RNC5 Multi-Bluetooth Karaoke Star, con Bluetooth y modo karaoke, para llevar la diversión y el sonido a otro nivel.',99,799.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/29308534-220-220/20353605.jpg?v=638586345198430000'),(100,'Audífonos In Ear SONY WF-LS900N Blanco','Audífonos In Ear SONY WF-LS900N Blanco, con cancelación activa de ruido y excelente calidad de sonido, perfectos para disfrutar de tu música sin interrupciones.',99,649.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/26504011-220-220/20281537.jpg?v=638211176289200000'),(101,'Minicomponente SONY MHC-V13 Negro','Minicomponente SONY MHC-V13 Negro, un sistema de audio compacto con un sonido potente y conexión Bluetooth para disfrutar de la música sin cables.',99,799.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/26503951-220-220/20210084.jpg?v=638211175388700000'),(102,'Minicomponentes SONY MHC-V43D Negro','Minicomponentes SONY MHC-V43D Negro, un sistema de sonido con múltiples funciones, ideal para fiestas, con Bluetooth y efectos de luz.',99,1299.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/26503920-220-220/20202615.jpg?v=638211175060200000'),(103,'Torre de Sonido SAMSUNG Bluetooth 1500W MX-T70/PE','Torre de Sonido SAMSUNG Bluetooth 1500W MX-T70/PE, torre de sonido potente con conexión Bluetooth y efectos de luces, ideal para fiestas y eventos.',99,1199.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/28522905-220-220/20198082.jpg?v=638375469698700000'),(104,'Parlante Bluetooth JBL Xtreme 4 Portatil Extra Bass Pro IP67','Parlante Bluetooth JBL Xtreme 4 Portatil Extra Bass Pro IP67, parlante resistente al agua con un sonido envolvente, ideal para aventuras al aire libre.',99,1189.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/29378283-220-220/imageUrl_1.jpg?v=638600873066000000'),(105,'Audífonos Inalámbricos In-Ear HUAWEI FreeBuds SE 2 Negro','Audífonos Inalámbricos In-Ear HUAWEI FreeBuds SE 2 Negro, diseño ergonómico con sonido claro y conexiones rápidas para un estilo de vida móvil.',99,94.90000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/29221484-220-220/image-37490b54f8be433fa828838ca7584f8a.jpg?v=638564451282930000'),(106,'Audífonos In Ear SKULLCANDY Grind Tw','Audífonos In Ear SKULLCANDY Grind Tw, audífonos compactos y ligeros con sonido potente, ideales para quienes buscan un rendimiento superior.',99,169.00000000,3,'https://plazavea.vteximg.com.br/arquivos/ids/29089712-220-220/20404199.jpg?v=638521122322300000');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resena`
--

DROP TABLE IF EXISTS `resena`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resena` (
  `id_resena` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `comentario` text,
  `puntuacion` decimal(2,1) DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  PRIMARY KEY (`id_resena`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `resena_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `resena_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resena`
--

LOCK TABLES `resena` WRITE;
/*!40000 ALTER TABLE `resena` DISABLE KEYS */;
INSERT INTO `resena` VALUES (1,1,'El teléfono es increíble, muy rápido y buena batería.',4.8,1),(2,2,'La pizza estaba deliciosa, me gustó mucho la masa.',4.5,4),(3,1,'El producto es excelente!',5.0,1),(4,1,'El producto es Terrible!',1.0,2),(5,1,'El producto esta decente',3.5,3),(6,1,'El producto esta decente',4.0,6),(7,1,'El producto esta decente',4.0,6),(8,1,'El producto esta meh',4.0,6),(9,1,'El producto esta meh',4.0,6),(10,1,'El producto es una porqueria',4.0,6),(11,3,'hola',3.0,1);
/*!40000 ALTER TABLE `resena` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROL_USUARIO'),(2,'ROL_USUARIO'),(3,'ROL_USUARIO'),(4,'ROL_USUARIO'),(5,'ROL_USUARIO'),(6,'ROL_USUARIO'),(7,'ROL_USUARIO'),(8,'ROL_USUARIO'),(9,'ROL_USUARIO'),(10,'ROL_USUARIO'),(11,'ROL_USUARIO'),(12,'ROL_USUARIO'),(13,'ROL_USUARIO'),(14,'ROL_USUARIO'),(15,'ROL_USUARIO'),(16,'ROL_USUARIO'),(17,'ROL_USUARIO'),(18,'ROL_USUARIO'),(19,'ROL_USUARIO'),(20,'ROL_USUARIO');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `telefono` varchar(11) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nombre_usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Juan','Pérez','juanp','password123','5551234567','1990-05-15'),(2,'María','Gómez','mariag','password456','5559876543','1985-09-23'),(3,'Joaquin','Hidalgo','joaquin@correo.com','$2a$10$GShaE5pRtK8uUCT9XxtsceLYxhrS0FkH8bpB8BPyxdn0rC6uvH2uW','123456789','1998-09-22'),(4,'Joaquin','Hidalgo','joaquin1@correo.com','$2a$10$.47GA8drH2sHSWXL5fsMvewHM.DPpDtS2PJF..jJ5n/QKpi7epwrS','123456789','1998-09-22'),(5,'Joaquin','Hidalgo','joaquin3@correo.com','$2a$10$CO7UApUXbotb7E.0ly9d3eWnxdNebttd9FJPT9MVuUxyMWx8LK2Mu','123456789','1998-09-22'),(6,'Joaquin','Enrique','joaquin9@correo.com','$2a$10$favWPsfSI2RDL.XvrfoO3.vuECTEsz0RXxnSO1GDzk3gr2Q2DGrvG','999999999','1993-11-27'),(7,'Joaquin','Hidalgo','joaquin4@correo.com','$2a$10$kmBRPfeIT7Hg1ot9Qf7cEuL60ROQYbxq/AvMfwMWj47gUdoZ3CBXO','123456789','1998-09-22'),(8,'joaquin','enrique','123@123.com','$2a$10$3.MHG2JsaV5G019CdboWXuK2cW9LAaQLZOoQ7Jf032BNW0hrz5WSi','999999999','1969-11-24'),(9,'joaquin','enrique','1234@123.com','$2a$10$a6H4W/y4XIa.sFCQJSAo6OOLzCcjcBrqiUsAo5kA7XUhzZmLH67z6','999999999','1969-11-24'),(10,'John','Doe','johndoe@example.com','$2a$10$.OLTfqoE38pPJ3zm5pMFxe0Q9GKgmYJh7cqlzujsZD2fIMUbj3eVa','1234567890','2024-11-26'),(11,'John','Doe','johndoe1@example.com','$2a$10$qyDegEVQLwuJ5wKjQCaxZuE1fby9JQj5F./tBNEG4uL1f22.JUfIq','1234567890','2024-11-22'),(12,'John','Doe','johndoe2@example.com','$2a$10$tHGWsjz8uVom5LZTcavGO.V1SiNuOVuJynmY1JBuYC4lRhldwkM8O','1234567890','2024-11-22'),(13,'John','Doe','johndoe3@example.com','$2a$10$0AtUcXToXp6VrfJ3p2SyJuLzOYz0emCi4UwRXgUxupFbejUn26u.a','1234567890','2024-11-22'),(14,'John','Doe','johndoe5@example.com','$2a$10$p0ibtm.0mSxJEZKCT9OIrONeXu/mdaejoTiribTNa4ZkKTkYsUlyC','1234567890','2024-11-22'),(15,'asd','adassd','asd@asd.com','$2a$10$NjKAInnMnLrxGFrrDAOh0.mmyX7LaRgTnUgyhzCJ8dCSQX36zSNiC','123','2024-11-22'),(16,'John','Doe','johndoe6@example.com','$2a$10$z4ss2D2g12M/meyH0k0YHuSPeU2LWWro7LytIStr.28a61lww5uEK','1234567890','2024-11-22'),(17,'John','Doe','johndoe9@example.com','$2a$10$Y5fKBIBTLPJeD76Xx1nzwefA7Wh.FdgvZOqFREorysWfVfzlNk5De','1234567890','2024-11-22'),(18,'John','Doe','johndoe99@example.com','$2a$10$Zi0..rVXLg02Qf983wew8u3TVuZctGy5ZS2rvv7m36.PMMPQGnHFO','1234567890','2024-11-22'),(19,'John','Doe','johndoe69@example.com','$2a$10$tukzoOQRoVgFhAYvBgebvOXD/BlC2oWE7dNSgh/P9RMdj/CalNzXC','1234567890','2024-11-22'),(20,'John','Doe','johndoe696@example.com','$2a$10$xr8BeT.vihfDPQq9Y6.z0udkIHh7PHZx3bRFr.SZCKGcsUw8m1nB6','1234567890','2024-11-22'),(21,'Joaquin','Hidalgo','joaquin7@correo.com','$2a$10$FZLv.5Int2MPuUgVBAkgDOEt1I19ypqo6IJJFE22SdbEefa19a2tO','123456789','1998-09-22'),(22,'Joaquin','Hidalgo','test@test.com','$2a$10$m8k5PyCCvZbcW03QdAyJnOMGzZohm.8jP4rqf2G57k7twhLu0kNpS','123456789','1998-09-22');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `id_rol` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (1,3),(2,4),(3,5),(4,6),(5,7),(6,8),(7,9),(8,10),(9,11),(10,12),(11,13),(12,14),(13,15),(14,16),(15,17),(16,18),(17,19),(18,20),(19,21),(20,22);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-08 22:27:49
