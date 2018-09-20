package ru.demi.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.demi.util.HibernateUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ItemAccessPropertyTest {

    private static Session session;
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

    @Test
    public void shouldSetNameBySetter() {
        session.beginTransaction();

        Item item = new Item();
        String name = "item";
        item.setName(name);
        item.setAuctionEnd(Date.from(Instant.now().plus(Duration.ofDays(2))));
        session.persist(item);

        session.getTransaction().commit();
        session.clear();

        Query<Item> query = session.createQuery("select i from Item i where i.id = :id", Item.class);
        query.setParameter("id", item.getId());
        Item saved = query.list().iterator().next();

        assertEquals("auction: " + name, saved.getName());
    }
}
