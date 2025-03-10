<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nuevo Proyecto</title>
</head>
<body>
    <h1>Agregar Nuevo Proyecto</h1>

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

    <br>
    <a href="proyectos">Volver a Proyectos</a>
</body>
</html>
