CREATE DATABASE gestion_proyectos;
Use gestion_proyectos;

-- Crear la tabla proyectos
CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proyecto VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATE,
    fecha_fin DATE,
    estado ENUM('en curso', 'completado') NOT NULL
);

-- Crear la tabla tareas
CREATE TABLE tareas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_proyecto INT,
    descripcion_tarea TEXT,
    responsable VARCHAR(255),
    fecha_inicio DATE,
    fecha_fin DATE,
    estado ENUM('pendiente', 'en progreso', 'completada') NOT NULL,
    FOREIGN KEY (id_proyecto) REFERENCES proyectos(id)
);
