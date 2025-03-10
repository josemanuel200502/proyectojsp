/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.ProyectoDAO;
import com.mycompany.mavenproject1.Proyecto;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class EliminarServlet extends HttpServlet {

    private ProyectoDAO proyectoDAO;

    @Override
    public void init() throws ServletException {
        proyectoDAO = new ProyectoDAO();
    }

    // Eliminar un proyecto por ID
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                Proyecto proyecto = proyectoDAO.getProyectoById(id);
                if (proyecto != null) {
                    proyectoDAO.delete(proyecto);
                    response.sendRedirect("proyectos");
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
