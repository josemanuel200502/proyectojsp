package servlets;

import DAO.TareaDAO;
import DAO.ProyectoDAO;
import com.mycompany.mavenproject1.Tarea;
import com.mycompany.mavenproject1.Proyecto;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TareaServlet extends HttpServlet {

    private TareaDAO tareaDAO;
    private ProyectoDAO proyectoDAO;

    @Override
    public void init() throws ServletException {
        tareaDAO = new TareaDAO();
        proyectoDAO = new ProyectoDAO();  // Asegúrate de inicializar el ProyectoDAO
    }

    // Mostrar tareas de un proyecto
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String proyectoIdParam = request.getParameter("proyectoId");

        if (proyectoIdParam != null) {
            try {
                Long proyectoId = Long.parseLong(proyectoIdParam);
                Proyecto proyecto = proyectoDAO.getProyectoById(proyectoId); // Obtener proyecto desde el DAO

                if (proyecto != null) {
                    List<Tarea> tareas = tareaDAO.getTareasByProyecto(proyecto.getId());  // Obtener las tareas para el proyecto usando el ID
                    request.setAttribute("tareas", tareas);
                    request.setAttribute("proyecto", proyecto);
                    request.getRequestDispatcher("/tareas.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Proyecto no encontrado.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de proyecto inválido.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro proyectoId.");
        }
    }

    // Crear una nueva tarea para un proyecto
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String descripcionTarea = request.getParameter("descripcionTarea");
    String responsable = request.getParameter("responsable");
    String fechaInicioParam = request.getParameter("fechaInicio");
    String fechaFinParam = request.getParameter("fechaFin");
    String estado = request.getParameter("estado");
    String proyectoIdParam = request.getParameter("proyectoId");

    // Asegúrate de convertir las fechas de String a Date
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaInicio = null;
    Date fechaFin = null;

    try {
        // Convertir las cadenas a fechas
        fechaInicio = sdf.parse(fechaInicioParam);
        fechaFin = sdf.parse(fechaFinParam);
    } catch (ParseException e) {
        // Manejar el error de parseo
        e.printStackTrace();  // O loguear el error
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fecha inválida.");
        return;  // Termina el método si ocurre un error
    }

    // Obtener el proyecto asociado
    Long proyectoId = Long.parseLong(proyectoIdParam);
    Proyecto proyecto = proyectoDAO.getProyectoById(proyectoId);  // Asegúrate de tener el DAO correctamente inicializado

    if (proyecto != null) {
        Tarea tarea = new Tarea(descripcionTarea, responsable, fechaInicio, fechaFin, estado, proyecto);
        tareaDAO.save(tarea);  // Asegúrate de tener el DAO para guardar la tarea
        response.sendRedirect("tareas?proyectoId=" + proyectoId);  // Redirige a las tareas del proyecto
    } else {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Proyecto no encontrado.");
    }
}}
