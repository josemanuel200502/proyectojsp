<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.mycompany.mavenproject1.Proyecto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Proyectos</title>
</head>
<body>
    <h1>Proyectos</h1>
    
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="proyecto" items="${proyectos}">
            <tr>
                <td>${proyecto.nombre}</td>
                <td>${proyecto.descripcion}</td>
                <td>${proyecto.fechaInicio}</td>
                <td>${proyecto.fechaFin}</td>
                <td>${proyecto.estado}</td>
                <td>
                    <a href="tareas?proyectoId=${proyecto.id}">Ver Tareas</a> | 
                    <a href="eliminarProyecto?id=${proyecto.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar Proyecto</h2>
    <form action="proyectos" method="post">
        <label>Nombre:</label> <input type="text" name="nombre" required><br>
        <label>Descripción:</label> <input type="text" name="descripcion" required><br>
        <label>Fecha Inicio:</label> <input type="date" name="fecha_inicio" required><br>
        <label>Fecha Fin:</label> <input type="date" name="fecha_fin" required><br>
        <label>Estado:</label>
        <select name="estado">
            <option value="pendiente">Pendiente</option>
            <option value="en progreso">En Progreso</option>
            <option value="completado">Completado</option>
        </select><br>
        <input type="submit" value="Guardar">
    </form>
</body>
</html>
