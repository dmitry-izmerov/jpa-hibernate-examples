package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BidirectionalAssociationTest extends BaseEntityTest {

    @Test
    public void shouldSetAssocation() {
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

        Item saved = session.find(Item.class, item.getId());

        assertEquals(1, saved.getBids().size());
    }
}
