package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Paniers;

public class HibernateSessionFactory {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Créer la SessionFactory à partir du fichier hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Paniers.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
