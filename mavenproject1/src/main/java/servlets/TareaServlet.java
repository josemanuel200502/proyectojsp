package servlets;

import DAO.ProyectoDAO;
import DAO.TareaDAO;
import com.mycompany.mavenproject1.Tarea;
import com.mycompany.mavenproject1.Proyecto;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TareaServlet extends HttpServlet {

    private TareaDAO tareaDAO;

    @Override
    public void init() throws ServletException {
        tareaDAO = new TareaDAO();  // Inicializa el DAO de Tarea
    }

    // Mostrar las tareas
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = request.getParameter("estado");
        List<Tarea> tareas;

        if (estado != null) {
            tareas = tareaDAO.getTareasPorEstado(estado);
        } else {
            tareas = tareaDAO.getAllTareas();
        }

        request.setAttribute("tareas", tareas);
        request.getRequestDispatcher("/tareas.jsp").forward(request, response);
    }

    // Registrar una nueva tarea
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String fechaInicioStr = request.getParameter("fecha_inicio");
        String fechaFinStr = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");
        Long proyectoId = Long.parseLong(request.getParameter("proyecto_id"));

        // Convertir las fechas de tipo String a Date usando SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = null;
        Date fechaFin = null;
        
        try {
            fechaInicio = sdf.parse(fechaInicioStr);
            fechaFin = sdf.parse(fechaFinStr);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha inválido.");
            return;
        }

        // Recuperar el proyecto relacionado
        Proyecto proyecto = new ProyectoDAO().getProyectoById(proyectoId);

        if (proyecto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Proyecto no encontrado.");
            return;
        }

        // Crear la nueva tarea
        Tarea tarea = new Tarea(descripcion, "Responsable", fechaInicio, fechaFin, estado, proyecto);
        tareaDAO.save(tarea);

        response.sendRedirect("tareas");
    }
}
