package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class OrphanRemovalTest extends BaseEntityTest {

    @Test
    public void shouldRemoveOrphan() {
        session.beginTransaction();

        Item item = new Item();
        String name = "item";
        item.setName(name);
        item.setAuctionEnd(Date.from(Instant.now().plus(Duration.ofDays(2))));

        Bid bid = new Bid();
        bid.setAmount(BigDecimal.ONE);
        bid.setItem(item);
        item.getBids().add(bid);

        session.persist(item);

        session.getTransaction().commit();
        session.clear();

        session.beginTransaction();

        Item saved = session.find(Item.class, item.getId());
        Bid next = saved.getBids().iterator().next();
        saved.getBids().remove(next);

        session.getTransaction().commit();
        session.clear();

        Item found = session.find(Item.class, item.getId());

        assertEquals(0, found.getBids().size());
    }
}
