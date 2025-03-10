/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Windows
 */

import com.mycompany.mavenproject1.Tarea;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;


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

    // Método para obtener todas las tareas de un proyecto
    public List<Tarea> getTareasByProyecto(Long proyectoId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Tarea> tareas = null;
        try {
            tareas = session.createQuery("from Tarea t where t.proyecto.id = :proyectoId", Tarea.class)
                    .setParameter("proyectoId", proyectoId)
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
