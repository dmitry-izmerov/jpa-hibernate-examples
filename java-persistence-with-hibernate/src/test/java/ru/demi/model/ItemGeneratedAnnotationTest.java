package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class ItemGeneratedAnnotationTest extends BaseEntityTest {

    @Test
    public void shouldSetGeneratedValues() {
        session.beginTransaction();

        Item item = new Item();
        String name = "item";
        item.setName(name);
        item.setAuctionEnd(Date.from(Instant.now().plus(Duration.ofDays(2))));
        session.persist(item);

        session.getTransaction().commit();
        session.clear();

        Item saved = session.find(Item.class, item.getId());
        assertNotNull(saved.getCreatedOn());
        assertNotNull(saved.getInitialPrice());
    }
}
