USE ALFASHOP;
CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    nombre_usuario VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(11),
    fecha_nacimiento DATE
);

-- Tabla de Pedido (Orden)
CREATE TABLE pedido (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    fecha_creacion TIMESTAMP NOT NULL,
    metodo_pago VARCHAR(255) NOT NULL,
    total_monto NUMERIC(38,2) NOT NULL,
    id_usuario INT,
    estado VARCHAR(255),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla de Pago
CREATE TABLE pago (
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    monto NUMERIC(32,8) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    metodo_pago VARCHAR(255) NOT NULL,
    id_pedido INT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
);

-- Tabla de Categoría
CREATE TABLE categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255)
);

-- Tabla de Producto
CREATE TABLE producto (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    stock INT DEFAULT 0,
    precio NUMERIC(32,8),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- Tabla de Pedido-Producto (Detalle de la Compra)
CREATE TABLE pedido_producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cantidad DOUBLE NOT NULL,
    precio_total NUMERIC(38,2) NOT NULL,
    id_pedido INT,
    id_producto INT,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

-- Tabla de Reseña
CREATE TABLE resena (
    id_resena INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    comentario TEXT,
    puntuacion NUMERIC(2,1),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla de Reseña-Producto
CREATE TABLE producto_resena (
    id_producto INT,
    id_resena INT,
    PRIMARY KEY (id_producto, id_resena),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_resena) REFERENCES resena(id_resena)
);
-- Inserciones en la tabla de Categoría
INSERT INTO categoria (nombre, descripcion) VALUES
('Electrónicos', 'Productos de tecnología como teléfonos, computadoras, etc.'),
('Comida', 'Alimentos y bebidas de todo tipo'),
('Accesorios', 'Complementos y accesorios para diferentes productos');

-- Inserciones en la tabla de Producto
INSERT INTO producto (nombre, descripcion, stock, precio, id_categoria) VALUES
('Smartphone', 'Teléfono inteligente con 128GB de almacenamiento', 100, 699.99, 1),
('Laptop', 'Computadora portátil con procesador Intel i7', 50, 1199.99, 1),
('Audífonos Bluetooth', 'Auriculares inalámbricos con cancelación de ruido', 200, 149.99, 3),
('Pizza Margherita', 'Pizza clásica con tomate, mozzarella y albahaca', 150, 8.99, 2),
('Hamburguesa Doble', 'Hamburguesa con doble carne y queso', 120, 5.99, 2),
('Mouse Gamer', 'Ratón inalámbrico para juegos con luces LED', 300, 59.99, 1);

-- Inserciones en la tabla de Usuario
INSERT INTO usuario (nombre, apellido, nombre_usuario, contrasena, telefono, fecha_nacimiento) VALUES
('Juan', 'Pérez', 'juanp', 'password123', '5551234567', '1990-05-15'),
('María', 'Gómez', 'mariag', 'password456', '5559876543', '1985-09-23');

-- Inserciones en la tabla de Pedido (Orden)
INSERT INTO pedido (fecha_creacion, metodo_pago, total_monto, id_usuario, estado) VALUES
(NOW(), 'PAYPAL', 714.98, 1, 'Completado'),
(NOW(), 'VISA', 14.98, 2, 'Completado');

-- Inserciones en la tabla de Pago
INSERT INTO pago (monto, fecha_creacion, metodo_pago, id_pedido) VALUES
(714.98, NOW(), 'PAYPAL', 1),
(14.98, NOW(), 'VISA', 2);

-- Inserciones en la tabla de Pedido-Producto (Detalle de Pedido)
INSERT INTO pedido_producto (cantidad, precio_total, id_pedido, id_producto) VALUES
(1, 699.99, 1, 1),  -- Smartphone en el primer pedido
(1, 149.99, 1, 3),  -- Audífonos en el primer pedido
(1, 8.99, 2, 4),    -- Pizza en el segundo pedido
(1, 5.99, 2, 5);    -- Hamburguesa en el segundo pedido

-- Inserciones en la tabla de Reseña
INSERT INTO resena (id_usuario, comentario, puntuacion) VALUES
(1, 'El teléfono es increíble, muy rápido y buena batería.', 4.8),
(2, 'La pizza estaba deliciosa, me gustó mucho la masa.', 4.5);

-- Inserciones en la tabla de Producto-Reseña
INSERT INTO producto_resena (id_producto, id_resena) VALUES
(1, 1),  -- Reseña del Smartphone
(4, 2);  -- Reseña de la Pizza
