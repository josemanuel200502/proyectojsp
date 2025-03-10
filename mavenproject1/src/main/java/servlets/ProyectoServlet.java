package servlets;

import DAO.ProyectoDAO;
import com.mycompany.mavenproject1.Proyecto;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProyectoServlet extends HttpServlet {

    private ProyectoDAO proyectoDAO;

    @Override
    public void init() throws ServletException {
        proyectoDAO = new ProyectoDAO();
    }

    // Mostrar los proyectos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = request.getParameter("estado");
        List<Proyecto> proyectos;

        if (estado != null) {
            proyectos = proyectoDAO.getProyectosPorEstado(estado);
        } else {
            proyectos = proyectoDAO.getAllProyectos();
        }

        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("/proyectos.jsp").forward(request, response);
    }

    // Registrar un nuevo proyecto
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String estado = request.getParameter("estado");

        Proyecto proyecto = new Proyecto(nombre, descripcion, fechaInicio, fechaFin, estado);
        proyectoDAO.save(proyecto);

        response.sendRedirect("proyectos");
    }
}

