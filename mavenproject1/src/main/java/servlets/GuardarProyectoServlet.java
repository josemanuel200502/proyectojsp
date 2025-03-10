package servlets;

import com.mycompany.mavenproject1.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class GuardarProyectoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombreProyecto = request.getParameter("nombreProyecto");
        String descripcion = request.getParameter("descripcion");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        String estado = request.getParameter("estado");

        // Crear un nuevo objeto Proyecto
        Proyecto proyecto = new Proyecto(nombreProyecto, descripcion, fechaInicio, fechaFin, estado);

        // Usar Hibernate para guardar el proyecto
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(proyecto);  // Guardamos el proyecto en la base de datos
                transaction.commit();  // Confirmamos la transacción
                response.sendRedirect("Proyectos.jsp");  // Redirige a la página de proyectos
            } catch (Exception e) {
                transaction.rollback();  // Si ocurre un error, revertimos la transacción
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al guardar el proyecto.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para guardar nuevos proyectos en la base de datos usando Hibernate.";
    }
}
