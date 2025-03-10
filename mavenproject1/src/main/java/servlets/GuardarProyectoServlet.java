package servlets;

import com.mycompany.mavenproject1.Proyecto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/guardarProyecto")
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

        // Persistir el proyecto en la base de datos usando Hibernate
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionProyectoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(proyecto); // Persistir el objeto Proyecto
        em.getTransaction().commit();
        em.close();

        // Redirigir al usuario a la página de proyectos después de guardar
        response.sendRedirect("Proyectos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para guardar nuevos proyectos en la base de datos";
    }
}
