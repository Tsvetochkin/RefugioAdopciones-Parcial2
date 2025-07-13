DROP TABLE IF EXISTS empleado;
DROP TABLE IF EXISTS adoptante;

CREATE TABLE empleado (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(255),
                          edad INT,
                          direccion VARCHAR(255),
                          fecha_nacimiento VARCHAR(255),
                          password VARCHAR(255)
);

CREATE TABLE adoptante (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255),
                           edad INT,
                           direccion VARCHAR(255),
                           fecha_nacimiento VARCHAR(255)
);