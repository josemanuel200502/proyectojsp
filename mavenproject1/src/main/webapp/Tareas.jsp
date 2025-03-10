<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.mycompany.mavenproject1.Tarea" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Tareas</title>
</head>
<body>
    <h1>Tareas del Proyecto</h1>

    <table border="1">
        <tr>
            <th>Descripción</th>
            <th>Responsable</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="tarea" items="${tareas}">
            <tr>
                <td>${tarea.descripcionTarea}</td>
                <td>${tarea.responsable}</td>
                <td>${tarea.fechaInicio}</td>
                <td>${tarea.fechaFin}</td>
                <td>${tarea.estado}</td>
                <td>
                    <a href="eliminarTarea?id=${tarea.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Agregar Tarea</h2>
    <form action="tareas" method="post">
        <input type="hidden" name="proyectoId" value="${param.proyectoId}">
        <label>Descripción:</label> <input type="text" name="descripcion" required><br>
        <label>Responsable:</label> <input type="text" name="responsable" required><br>
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

    <br>
    <a href="proyectos">Volver a Proyectos</a>
</body>
</html>
