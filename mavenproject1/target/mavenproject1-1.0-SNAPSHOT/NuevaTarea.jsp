<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Tarea</title>
    
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

    <!-- Formulario de nueva tarea -->
    <div class="container form-container">
        <h1 class="text-center mb-4">Agregar Nueva Tarea</h1>

        <form action="tareas" method="post">
            <input type="hidden" name="proyectoId" value="${param.proyectoId}">

            <div class="mb-3">
                <label class="form-label">Descripción:</label>
                <input type="text" class="form-control" name="descripcion" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Responsable:</label>
                <input type="text" class="form-control" name="responsable" required>
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
                <button type="submit" class="btn btn-primary">Guardar Tarea</button>
            </div>
        </form>
    </div>

    <!-- Enlace para volver a tareas -->
    <div class="container mt-4 text-center">
        <a href="Tareas.jsp?proyectoId=${param.proyectoId}" class="btn btn-outline-primary">Volver a Tareas</a>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
