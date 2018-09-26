package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class UnidirectionalManyToOneAssociationTest extends BaseEntityTest {

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

        session.persist(bid);

        session.getTransaction().commit();
        session.clear();

        Bid saved = session.find(Bid.class, bid.getId());

        assertNotNull(saved.getItem());
    }
}
