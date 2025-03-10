package DAO;

import com.mycompany.mavenproject1.Tarea;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class TareaDAO {

    // Método para guardar una nueva tarea
    public void save(Tarea tarea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(tarea);  // Guardamos la tarea en la base de datos
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para obtener todas las tareas
    public List<Tarea> getAllTareas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Tarea> tareas = null;
        try {
            tareas = session.createQuery("from Tarea", Tarea.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tareas;
    }

    // Método para obtener tareas por estado
    public List<Tarea> getTareasPorEstado(String estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Tarea> tareas = null;
        try {
            tareas = session.createQuery("from Tarea t where t.estado = :estado", Tarea.class)
                            .setParameter("estado", estado)
                            .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tareas;
    }

    // Método para obtener una tarea por su ID
    public Tarea getTareaById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tarea tarea = null;
        try {
            tarea = session.get(Tarea.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tarea;
    }

    // Método para eliminar una tarea
    public void delete(Tarea tarea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(tarea);  // Eliminamos la tarea
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Método para actualizar una tarea
    public void update(Tarea tarea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(tarea);  // Actualizamos la tarea
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
