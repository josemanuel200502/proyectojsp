package servlets;

import DAO.ProyectoDAO;
import com.mycompany.mavenproject1.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import util.HibernateUtil;

public class EliminarProyectoServlet extends HttpServlet {

    private ProyectoDAO proyectoDAO;

    @Override
    public void init() throws ServletException {
        proyectoDAO = new ProyectoDAO();  // Inicializa el DAO de Proyecto
    }

    // Eliminar un proyecto por ID
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Proyecto proyecto = proyectoDAO.getProyectoById(id);  // Obtén el proyecto por ID

                if (proyecto != null) {
                    // Inicia una nueva sesión con Hibernate
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction transaction = session.beginTransaction();
                    
                    try {
                        // Elimina el proyecto
                        proyectoDAO.delete(proyecto);
                        transaction.commit();  // Confirma la transacción
                        response.sendRedirect("proyectos");  // Redirige a la lista de proyectos
                    } catch (Exception e) {
                        transaction.rollback();  // Si ocurre algún error, revierte la transacción
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el proyecto.");
                    } finally {
                        session.close();  // Cierra la sesión de Hibernate
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Proyecto no encontrado.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro ID.");
        }
    }
}
