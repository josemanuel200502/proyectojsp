package servlets;




import DAO.ProyectoDAO;
import com.mycompany.mavenproject1.Proyecto;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

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
                    // Elimina el proyecto usando el ProyectoDAO
                    proyectoDAO.delete(proyecto);  
                    response.sendRedirect("proyectos");  // Redirige a la lista de proyectos
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Proyecto no encontrado.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el proyecto.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro ID.");
        }
    }
}
