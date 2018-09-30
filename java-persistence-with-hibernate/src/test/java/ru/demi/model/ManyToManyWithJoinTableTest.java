package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ManyToManyWithJoinTableTest extends BaseEntityTest {

    @Test
    public void shouldSetAssocation() {
        session.beginTransaction();

        Category category = new Category()
            .setName("category");

        Item item = new Item();
        String name = "item";
        item.setName(name);
        item.setAuctionEnd(Date.from(Instant.now().plus(Duration.ofDays(2))));

        category.getItems().add(item);

        session.persist(category);

        session.getTransaction().commit();
        session.clear();

        Category saved = session.find(Category.class, category.getId());

        assertEquals(1, saved.getItems().size());
    }
}
