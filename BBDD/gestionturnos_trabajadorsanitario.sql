-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestionturnos
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `trabajadorsanitario`
--

DROP TABLE IF EXISTS `trabajadorsanitario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajadorsanitario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `contrasena` varchar(30) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  `CentroTrabajo` varchar(100) DEFAULT NULL,
  `Puesto` enum('ENFERMERO','MEDICO','TCAE') NOT NULL,
  `JornadaID` int(11) NOT NULL,
  `localidad` varchar(30) NOT NULL,
  `PreferenciasHorarias` text DEFAULT NULL,
  `DisponibilidadHorasExtras` tinyint(1) DEFAULT 0,
  `inicio_sesion` bit(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`email`),
  KEY `JornadaID_fk` (`JornadaID`),
  CONSTRAINT `JornadaID_fk` FOREIGN KEY (`JornadaID`) REFERENCES `jornada` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajadorsanitario`
--

LOCK TABLES `trabajadorsanitario` WRITE;
/*!40000 ALTER TABLE `trabajadorsanitario` DISABLE KEYS */;
INSERT INTO `trabajadorsanitario` VALUES (1,'Pablo','Ejemplo','pablo.ejemplo@mail.com','Pablo1234*','604386214','Tres Cantos','',1,'Madrid',NULL,0,_binary '\0'),(2,'Paula','Ejemplo','Paula.ejemplo@mail.com','Paula1234*','604386219','Cerceda','',2,'Madrid',NULL,0,_binary '\0'),(4,'Enrique','Muriel','talegon2000@gmail.com','oiuJKL789**','658695263','Hospital Universitario','',3,'Salamanca','Tardes',0,_binary '\0'),(6,'Alba','Muriel Talegon','DoctoraMuriel@gmail.com','BimbaYLola7*','655 256 986',NULL,'MEDICO',2,'Salamanca',NULL,0,_binary ''),(7,'Maria','Zarzoso','mzvaras@gmail.com','MiPerro89*/','665 258 596',NULL,'ENFERMERO',1,'Madrid',NULL,0,_binary ''),(8,'Sebastian','Gomez','Lavezzi@gmail.com','Misdf4*--/','665 258 596',NULL,'TCAE',1,'Avila',NULL,0,_binary ''),(9,'Oscar','Izquierdo sanchon','oscarSanitario@gmail.com','As9*8iujh','655 375 267',NULL,'ENFERMERO',2,'Salamanca',NULL,1,_binary ''),(11,'Oscar','Izquierdo sanchon','o11scarSanitario@gmail.com','As9*8iujh','655 375 267','Hospital Madroñal','ENFERMERO',2,'Salamanca','mañanas',1,_binary ''),(12,'Juanma','Garcia','Juanmi@gmail.com','asd123*/96S','655 859 685','Santa Teresa','MEDICO',1,'Gijón','tardes',1,_binary '');
/*!40000 ALTER TABLE `trabajadorsanitario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-15 22:45:10
