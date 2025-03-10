<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nueva Tarea</title>
</head>
<body>
    <h1>Agregar Nueva Tarea</h1>

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
    <a href="tareas?proyectoId=${param.proyectoId}">Volver a Tareas</a>
</body>
</html>
