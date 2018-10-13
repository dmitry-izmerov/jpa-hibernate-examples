package ru.demi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.demi.util.HibernateUtil;

public abstract class BaseEntityTest {
    protected static Session session;
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @AfterClass
    public static void turnDown() {
        session.close();
        sessionFactory.close();
    }
}
