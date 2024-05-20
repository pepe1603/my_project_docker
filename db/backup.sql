-- Creación de la tabla de categorías de contactos
CREATE TABLE categoria_contacto (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE
);

-- Creación de la tabla de contactos
CREATE TABLE contacto (
    id_contacto SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    direccion TEXT,
    id_categoria INT REFERENCES categoria_contacto(id_categoria) ON UPDATE CASCADE ON DELETE SET NULL
);

-- Crear la vista para filtrar los contactos por categoría
--CREATE VIEW view_contactos_por_categoria AS
--SELECT c.id_contacto, c.nombre, c.telefono, c.email, c.direccion, cc.nombre AS categoria
--FROM contacto c
--JOIN categoria_contacto cc ON c.id_categoria = cc.id_categoria;

-- Inserción de algunas categorías de contactos
INSERT INTO categoria_contacto (nombre) VALUES 
('Familia'),
('Amigos'),
('Trabajo');

-- Inserción de algunos contactos, asignándolos a diferentes categorías
INSERT INTO contacto (nombre, telefono, email, direccion, id_categoria) VALUES 
('Juan Pérez', '123456789', 'juan@example.com', 'Calle 123', 1), -- Familia
('María López', '987654321', 'maria@example.com', 'Avenida XYZ', 2), -- Amigos
('Pedro Gómez', '456789123', 'pedro@example.com', 'Plaza Principal', 3), -- Trabajo
('Ana Martínez', '789123456', 'ana@example.com', 'Calle Principal', 1), -- Familia
('Luis Rodríguez', '321654987', 'luis@example.com', 'Avenida Central', 2); -- Amigos
