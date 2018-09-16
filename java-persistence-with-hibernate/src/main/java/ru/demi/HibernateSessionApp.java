package ru.demi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.demi.model.Message;
import ru.demi.util.HibernateUtil;

public class HibernateSessionApp {
    public static void main(String[] args) {

        try (
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
        ) {
            session.beginTransaction();

            Message message = new Message();
            message.setText("new message");
            session.persist(message);

            session.getTransaction().commit();
        }
    }
}
