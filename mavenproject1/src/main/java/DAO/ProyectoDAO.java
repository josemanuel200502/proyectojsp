package DAO;

import com.mycompany.mavenproject1.Proyecto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class ProyectoDAO {

    // Método para guardar un nuevo proyecto
    public void save(Proyecto proyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(proyecto);  // Guardamos el proyecto en la base de datos
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> getAllProyectos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Proyecto> proyectos = null;
        try {
            proyectos = session.createQuery("from Proyecto", Proyecto.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return proyectos;
    }

    // Método para obtener proyectos por estado
    public List<Proyecto> getProyectosPorEstado(String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Proyecto> proyectos = null;
        try {
            proyectos = session.createQuery("from Proyecto p where p.estado = :estado", Proyecto.class)
                                .setParameter("estado", estado)
                                .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return proyectos;
    }

    // Método para obtener un proyecto por su ID
    public Proyecto getProyectoById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Proyecto proyecto = null;
        try {
            proyecto = session.get(Proyecto.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return proyecto;
    }

    // Método para eliminar un proyecto
    public void delete(Proyecto proyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(proyecto);  // Eliminamos el proyecto
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para actualizar un proyecto
    public void update(Proyecto proyecto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(proyecto);  // Actualizamos el proyecto
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
