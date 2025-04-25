-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-04-2025 a las 21:22:54
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestionturnos`
--
CREATE DATABASE IF NOT EXISTS `gestionturnos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestionturnos`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradorservicio`
--

CREATE TABLE `administradorservicio` (
  `ID` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Contraseña` varchar(255) NOT NULL,
  `PermisosEspeciales` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cambioturno`
--

CREATE TABLE `cambioturno` (
  `ID` int(11) NOT NULL,
  `TrabajadorSolicitanteID` int(11) NOT NULL,
  `TrabajadorAceptanteID` int(11) DEFAULT NULL,
  `JornadaID` int(11) NOT NULL,
  `FechaSolicitada` date NOT NULL,
  `FechaSolicitud` datetime NOT NULL,
  `FechaCambio` datetime DEFAULT NULL,
  `EstadoCambio` enum('Pendiente','Aceptado','Rechazado') NOT NULL DEFAULT 'Pendiente',
  `Activo` tinyint(1) NOT NULL DEFAULT 1 COMMENT 'Borrado logico de solicitudes'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cambioturno`
--

INSERT INTO `cambioturno` (`ID`, `TrabajadorSolicitanteID`, `TrabajadorAceptanteID`, `JornadaID`, `FechaSolicitada`, `FechaSolicitud`, `FechaCambio`, `EstadoCambio`, `Activo`) VALUES
(1, 1, NULL, 3, '2025-04-28', '2025-04-24 21:39:39', NULL, 'Pendiente', 0),
(2, 1, 2, 2, '2025-05-01', '2025-04-24 21:41:31', '2025-04-24 21:43:25', 'Aceptado', 1),
(3, 1, NULL, 3, '2025-04-28', '2025-04-24 21:39:39', NULL, 'Pendiente', 1),
(4, 1, NULL, 2, '2025-05-01', '2025-04-24 21:41:31', NULL, 'Pendiente', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornada`
--

CREATE TABLE `jornada` (
  `ID` int(11) NOT NULL,
  `Descripcion` varchar(30) NOT NULL,
  `Fecha` date NOT NULL,
  `HoraInicio` time NOT NULL,
  `HoraFin` time NOT NULL,
  `Estado` enum('Activo','Inactivo') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `jornada`
--

INSERT INTO `jornada` (`ID`, `Descripcion`, `Fecha`, `HoraInicio`, `HoraFin`, `Estado`) VALUES
(1, 'Mañana', '2025-04-24', '08:00:00', '16:00:00', 'Activo'),
(2, 'Tarde', '2025-04-24', '14:00:00', '22:00:00', 'Activo'),
(3, 'Noche', '2025-04-24', '22:00:00', '06:00:00', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajadorsanitario`
--

CREATE TABLE `trabajadorsanitario` (
  `ID` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Contraseña` varchar(255) NOT NULL,
  `Teléfono` varchar(15) DEFAULT NULL,
  `CentroTrabajo` varchar(100) DEFAULT NULL,
  `Puesto` enum('Médico','Enfermero','TCAE') NOT NULL,
  `JornadaID` int(11) NOT NULL,
  `Localidad` varchar(100) DEFAULT NULL,
  `PreferenciasHorarias` text DEFAULT NULL,
  `DisponibilidadHorasExtras` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trabajadorsanitario`
--

INSERT INTO `trabajadorsanitario` (`ID`, `Nombre`, `Apellido`, `Email`, `Contraseña`, `Teléfono`, `CentroTrabajo`, `Puesto`, `JornadaID`, `Localidad`, `PreferenciasHorarias`, `DisponibilidadHorasExtras`) VALUES
(1, 'Pablo', 'Ejemplo', 'pablo.ejemplo@mail.com', 'Pablo1234*', '604386214', 'Tres Cantos', 'Enfermero', 1, 'Madrid', NULL, 0),
(2, 'Paula', 'Ejemplo', 'Paula.ejemplo@mail.com', 'Paula1234*', '604386219', 'Cerceda', 'TCAE', 2, 'Madrid', NULL, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `ID` int(11) NOT NULL,
  `Modelo` varchar(50) NOT NULL,
  `PlazasDisponibles` tinyint(4) NOT NULL,
  `PropietarioID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `ID` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Origen` varchar(100) NOT NULL,
  `Destino` varchar(100) NOT NULL,
  `VehiculoID` int(11) NOT NULL,
  `PlazasOcupadas` tinyint(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradorservicio`
--
ALTER TABLE `administradorservicio`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indices de la tabla `cambioturno`
--
ALTER TABLE `cambioturno`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `TrabajadorSolicitanteID` (`TrabajadorSolicitanteID`),
  ADD KEY `TrabajadorAceptanteID` (`TrabajadorAceptanteID`),
  ADD KEY `JornadaID` (`JornadaID`);

--
-- Indices de la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `trabajadorsanitario`
--
ALTER TABLE `trabajadorsanitario`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD KEY `JornadaID_fk` (`JornadaID`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `PropietarioID` (`PropietarioID`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `VehiculoID` (`VehiculoID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administradorservicio`
--
ALTER TABLE `administradorservicio`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cambioturno`
--
ALTER TABLE `cambioturno`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `jornada`
--
ALTER TABLE `jornada`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `trabajadorsanitario`
--
ALTER TABLE `trabajadorsanitario`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `viaje`
--
ALTER TABLE `viaje`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cambioturno`
--
ALTER TABLE `cambioturno`
  ADD CONSTRAINT `cambioturno_ibfk_1` FOREIGN KEY (`TrabajadorSolicitanteID`) REFERENCES `trabajadorsanitario` (`ID`),
  ADD CONSTRAINT `cambioturno_ibfk_2` FOREIGN KEY (`TrabajadorAceptanteID`) REFERENCES `trabajadorsanitario` (`ID`),
  ADD CONSTRAINT `cambioturno_ibfk_3` FOREIGN KEY (`JornadaID`) REFERENCES `jornada` (`ID`);

--
-- Filtros para la tabla `trabajadorsanitario`
--
ALTER TABLE `trabajadorsanitario`
  ADD CONSTRAINT `JornadaID_fk` FOREIGN KEY (`JornadaID`) REFERENCES `jornada` (`ID`);

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`PropietarioID`) REFERENCES `trabajadorsanitario` (`ID`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`VehiculoID`) REFERENCES `vehiculo` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
