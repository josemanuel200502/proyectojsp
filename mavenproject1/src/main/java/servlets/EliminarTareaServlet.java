package servlets;

import DAO.TareaDAO;
import com.mycompany.mavenproject1.Tarea;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import util.HibernateUtil;

public class EliminarTareaServlet extends HttpServlet {

    private TareaDAO tareaDAO;

    @Override
    public void init() throws ServletException {
        tareaDAO = new TareaDAO();  // Inicializa el DAO de Tarea
    }

    // Eliminar una tarea por ID
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Tarea tarea = tareaDAO.getTareaById(id);  // Obtén la tarea por ID

                if (tarea != null) {
                    // Inicia una nueva sesión con Hibernate
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();
                    
                    try {
                        // Elimina la tarea
                        tareaDAO.delete(tarea);
                        transaction.commit();  // Confirma la transacción
                        response.sendRedirect("tareas");  // Redirige a la lista de tareas
                    } catch (Exception e) {
                        transaction.rollback();  // Si ocurre algún error, revierte la transacción
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar la tarea.");
                    } finally {
                        session.close();  // Cierra la sesión de Hibernate
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tarea no encontrada.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro ID.");
        }
    }
}
