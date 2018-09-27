package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class OptionalOneToManyWithJoinTableTest extends BaseEntityTest {

    @Test
    public void shouldSetAssocation() {
        session.beginTransaction();

        User user = new User();
        Item item = new Item();
        String name = "item";
        item.setName(name);
        item.setAuctionEnd(Date.from(Instant.now().plus(Duration.ofDays(2))));

        user.addBoughtItem(item);

        session.persist(user);

        session.getTransaction().commit();
        session.clear();

        User saved = session.find(User.class, user.getId());

        assertEquals(1, saved.getBoughtItems().size());
    }
}
