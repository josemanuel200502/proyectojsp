package servlets;

import DAO.TareaDAO;
import com.mycompany.mavenproject1.Tarea;
import com.mycompany.mavenproject1.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuardarTareaServlet extends HttpServlet {

    private TareaDAO tareaDAO;

    @Override
    public void init() throws ServletException {
        tareaDAO = new TareaDAO();  // Inicializa el DAO de Tarea
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String descripcion = request.getParameter("descripcion");
        String responsable = request.getParameter("responsable");
        String fechaInicioStr = request.getParameter("fecha_inicio");
        String fechaFinStr = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");
        String proyectoIdStr = request.getParameter("proyectoId");

        try {
            // Validar que todos los parámetros estén presentes
            if (descripcion == null || descripcion.trim().isEmpty() ||
                responsable == null || responsable.trim().isEmpty() ||
                fechaInicioStr == null || fechaFinStr == null ||
                estado == null || estado.trim().isEmpty() ||
                proyectoIdStr == null || proyectoIdStr.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
                return;
            }

            // Parsear las fechas
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = sdf.parse(fechaInicioStr);
            Date fechaFin = sdf.parse(fechaFinStr);

            // Obtener el proyecto asociado
            Long proyectoId = Long.parseLong(proyectoIdStr);
            Proyecto proyecto = getProyectoById(proyectoId);  // Método que obtiene el proyecto por ID

            if (proyecto != null) {
                // Crear la nueva tarea
                Tarea tarea = new Tarea(descripcion, responsable, fechaInicio, fechaFin, estado, proyecto);

                // Guardar la tarea en la base de datos
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    Transaction transaction = session.beginTransaction();
                    tareaDAO.save(tarea);  // Método save en el DAO de tarea
                    transaction.commit();  // Confirmar transacción
                    response.sendRedirect("Tareas.jsp?proyectoId=" + proyectoId);  // Redirigir a la lista de tareas
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al guardar la tarea.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Proyecto no encontrado.");
            }
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha incorrecto.");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de proyecto inválido.");
        }
    }

    private Proyecto getProyectoById(Long proyectoId) {
        // Método para obtener el proyecto por ID desde la base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Proyecto.class, proyectoId);  // Obtén el proyecto de la base de datos
        }
    }
}
