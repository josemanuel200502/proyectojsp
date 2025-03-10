package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Configuración de Hibernate a partir del archivo hibernate.cfg.xml
            sessionFactory = new Configuration().configure().addAnnotatedClass(com.mycompany.mavenproject1.Proyecto.class)
                    .addAnnotatedClass(com.mycompany.mavenproject1.Tarea.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError("Error al inicializar Hibernate: " + e.getMessage());
        }
    }

    /**
     * Obtiene la SessionFactory.
     * @return La instancia de SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Obtiene una sesión de Hibernate.
     * @return Una nueva sesión de Hibernate.
     */
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Cierra la sesión de Hibernate.
     * @param session La sesión a cerrar.
     */
    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

    /**
     * Cierra la sesión de Hibernate y la SessionFactory.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
