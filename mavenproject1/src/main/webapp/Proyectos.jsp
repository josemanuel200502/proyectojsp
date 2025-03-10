<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.mycompany.mavenproject1.Proyecto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Proyectos</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .navbar {
            background-color: #007bff !important;
        }
        .navbar-brand, .nav-link {
            color: white !important;
        }
        .table thead {
            background-color: #007bff;
            color: white;
        }
        .table tbody tr:hover {
            background-color: #f1f1f1;
        }
        .form-container {
            background-color: white;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #007bff;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-danger {
            background-color: #dc3545;
            border: none;
        }
        .btn-danger:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">Gestión de Proyectos</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li>
                    <li class="nav-item"><a class="nav-link" href="Proyectos.jsp">Proyectos</a></li>
                    <li class="nav-item"><a class="nav-link" href="Tareas.jsp">Tareas</a></li>
                    <li class="nav-item"><a class="nav-link" href="NuevaTarea.jsp">Nueva Tarea</a></li>
                    <li class="nav-item"><a class="nav-link" href="nuevoProyecto.jsp">Nuevo Proyecto</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Tabla de proyectos -->
    <div class="container">
        <h1 class="text-center mb-4">Lista de Proyectos</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="proyecto" items="${proyectos}">
                    <tr>
                        <td>${proyecto.nombre}</td>
                        <td>${proyecto.descripcion}</td>
                        <td>${proyecto.fechaInicio}</td>
                        <td>${proyecto.fechaFin}</td>
                        <td>${proyecto.estado}</td>
                        <td>
                            <a href="tareas?proyectoId=${proyecto.id}" class="btn btn-info btn-sm">Ver Tareas</a> | 
                            <a href="eliminarProyecto?id=${proyecto.id}" class="btn btn-danger btn-sm">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Formulario para agregar proyecto -->
    <div class="container form-container">
        <h2>Agregar Proyecto</h2>
        <form action="proyectos" method="post">
            <div class="mb-3">
                <label class="form-label">Nombre:</label>
                <input type="text" class="form-control" name="nombre" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Descripción:</label>
                <input type="text" class="form-control" name="descripcion" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Fecha de Inicio:</label>
                <input type="date" class="form-control" name="fecha_inicio" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Fecha de Fin:</label>
                <input type="date" class="form-control" name="fecha_fin" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Estado:</label>
                <select class="form-select" name="estado" required>
                    <option value="pendiente">Pendiente</option>
                    <option value="en progreso">En Progreso</option>
                    <option value="completado">Completado</option>
                </select>
            </div>
            <div class="d-flex justify-content-center">
                <button type="submit" class="btn btn-primary">Guardar Proyecto</button>
            </div>
        </form>
    </div>

    <div class="container mt-4 text-center">
        <a href="index.jsp" class="btn btn-outline-primary">Volver a Inicio</a>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
