package ru.demi;

import lombok.extern.slf4j.Slf4j;
import ru.demi.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

@Slf4j
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("hiberApp");
            entityManager = entityManagerFactory.createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();

            // save new message
            // Message message = new Message();
            // message.setText("new message");
            // entityManager.persist(message);

            // implicitly update message
            List<Message> messages = entityManager.createQuery("select m from Message m", Message.class)
                .getResultList();
            log.info(messages.get(0).getText());

            messages.get(0).setText("text was changed!");

            tx.commit();
        } catch (Exception e) {
            if (Objects.nonNull(tx)) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
            if (Objects.nonNull(entityManagerFactory)) {
                entityManagerFactory.close();
            }
        }
    }
}
