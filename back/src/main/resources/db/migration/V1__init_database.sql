-- V1__init_schema.sql

-- Crear tabla 'categoria'
CREATE TABLE IF NOT EXISTS `categoria` (
    `id_categoria` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(50) NOT NULL,
    `descripcion` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id_categoria`)
);

-- Crear tabla 'usuario'
CREATE TABLE IF NOT EXISTS `usuario` (
     `id_usuario` INT NOT NULL AUTO_INCREMENT,
     `nombre` VARCHAR(100) NOT NULL,
     `apellido` VARCHAR(100) NOT NULL,
     `email` VARCHAR(100) NOT NULL,
     `contrasena` VARCHAR(255) NOT NULL,
     `telefono` VARCHAR(11) DEFAULT NULL,
     `fecha_nacimiento` DATE DEFAULT NULL,
     PRIMARY KEY (`id_usuario`),
     UNIQUE KEY `nombre_usuario` (`email`)
);
-- Crear la tabla 'rol'
CREATE TABLE IF NOT EXISTS `rol` (
                                     `id_rol` INT NOT NULL AUTO_INCREMENT,
                                     `nombre` VARCHAR(255) DEFAULT NULL,
                                     PRIMARY KEY (`id_rol`)
);

-- Crear la tabla 'usuario_rol'
CREATE TABLE IF NOT EXISTS `usuario_rol` (
                                             `id_rol` INT NOT NULL,
                                             `id_usuario` INT NOT NULL,
                                             PRIMARY KEY (`id_usuario`, `id_rol`),
                                             KEY `id_rol` (`id_rol`),
                                             CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
                                             CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);
CREATE TABLE IF NOT EXISTS `producto` (
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
);
-- Crear la tabla 'resena'
CREATE TABLE IF NOT EXISTS `resena` (
                                        `id_resena` INT NOT NULL AUTO_INCREMENT,
                                        `id_usuario` INT DEFAULT NULL,
                                        `comentario` TEXT,
                                        `puntuacion` DECIMAL(2,1) DEFAULT NULL,
                                        `id_producto` INT DEFAULT NULL,
                                        PRIMARY KEY (`id_resena`),
                                        KEY `id_usuario` (`id_usuario`),
                                        KEY `id_producto` (`id_producto`),
                                        CONSTRAINT `resena_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
                                        CONSTRAINT `resena_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
);

-- Crear la tabla 'pedido'
CREATE TABLE IF NOT EXISTS `pedido` (
                                        `id_pedido` INT NOT NULL AUTO_INCREMENT,
                                        `fecha_creacion` TIMESTAMP NOT NULL,
                                        `metodo_pago` VARCHAR(255) NOT NULL,
                                        `total_monto` DECIMAL(38,2) NOT NULL,
                                        `id_usuario` INT DEFAULT NULL,
                                        `estado` VARCHAR(255) DEFAULT NULL,
                                        PRIMARY KEY (`id_pedido`),
                                        KEY `id_usuario` (`id_usuario`),
                                        CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);
-- Crear la tabla 'pedido_producto'
CREATE TABLE IF NOT EXISTS `pedido_producto` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `cantidad` DOUBLE NOT NULL,
                                                 `precio_total` DECIMAL(38,2) NOT NULL,
                                                 `id_pedido` INT DEFAULT NULL,
                                                 `id_producto` INT DEFAULT NULL,
                                                 PRIMARY KEY (`id`),
                                                 KEY `id_pedido` (`id_pedido`),
                                                 KEY `id_producto` (`id_producto`),
                                                 CONSTRAINT `pedido_producto_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
                                                 CONSTRAINT `pedido_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
);
-- Crear la tabla 'pago'
CREATE TABLE IF NOT EXISTS `pago` (
                                      `id_pago` INT NOT NULL AUTO_INCREMENT,
                                      `monto` DECIMAL(32,8) NOT NULL,
                                      `fecha_creacion` TIMESTAMP NOT NULL,
                                      `metodo_pago` VARCHAR(255) NOT NULL,
                                      `id_pedido` INT DEFAULT NULL,
                                      PRIMARY KEY (`id_pago`),
                                      KEY `id_pedido` (`id_pedido`),
                                      CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`)
);