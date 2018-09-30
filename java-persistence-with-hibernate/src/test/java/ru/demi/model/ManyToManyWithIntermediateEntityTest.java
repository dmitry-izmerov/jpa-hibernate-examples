package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class ManyToManyWithIntermediateEntityTest extends BaseEntityTest {

    @Test
    public void shouldSetAssocation() {
        session.beginTransaction();

        Category category = new Category()
            .setName("category");
        session.persist(category);

        Item item = new Item();
        String name = "item";
        item.setName(name);
        item.setAuctionEnd(Date.from(Instant.now().plus(Duration.ofDays(2))));
        session.persist(item);

        CategorizedItem categorizedItem = new CategorizedItem("vasya", category, item);

        session.persist(categorizedItem);

        session.getTransaction().commit();
        session.clear();

        CategorizedItem saved = session.find(CategorizedItem.class, categorizedItem.getId());

        assertNotNull(saved.getCategory());
        assertNotNull(saved.getItem());
        assertNotNull(saved.getAddedBy());
        assertNotNull(saved.getAddedOn());
    }
}
