package DAO;

import com.mycompany.mavenproject1.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class ProyectoDAO {

    // Método para guardar un nuevo proyecto
    public void save(Proyecto proyecto) {
        // Utiliza try-with-resources para gestionar la sesión de Hibernate
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(proyecto);  // Guardamos el proyecto en la base de datos
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();  // Si ocurre un error, revierte la transacción
                e.printStackTrace();
            }
        }
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> getAllProyectos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Proyecto", Proyecto.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener proyectos por estado
    public List<Proyecto> getProyectosPorEstado(String estado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Proyecto p where p.estado = :estado", Proyecto.class)
                          .setParameter("estado", estado)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener un proyecto por su ID
    public Proyecto getProyectoById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Proyecto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para eliminar un proyecto
    public void delete(Proyecto proyecto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(proyecto);  // Eliminamos el proyecto
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();  // Si ocurre un error, revierte la transacción
                e.printStackTrace();
            }
        }
    }

    // Método para actualizar un proyecto
    public void update(Proyecto proyecto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(proyecto);  // Actualizamos el proyecto
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();  // Si ocurre un error, revierte la transacción
                e.printStackTrace();
            }
        }
    }
}
