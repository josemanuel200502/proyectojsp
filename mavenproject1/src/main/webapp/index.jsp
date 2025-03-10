<%-- 
    Document   : index.jsp
    Created on : 10 mar 2025, 15:23:39
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Principal</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                text-align: center;
                margin: 50px;
            }
            h1 {
                color: #333;
            }
            .btn {
                display: inline-block;
                padding: 10px 20px;
                margin: 10px;
                font-size: 16px;
                text-decoration: none;
                color: white;
                background-color: #007bff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <h1>Hola chiques</h1>
        <p>Selecciona una opción:</p>

        <a href="nuevaTarea.jsp" class="btn">Nueva Tarea</a>
        <a href="Proyectos.jsp" class="btn">Proyectos</a>
        <a href="Tareas.jsp" class="btn">Tareas</a>
        <a href="nuevoProyecto.jsp" class="btn">Nuevo Proyecto</a>
    </body>
</html>
