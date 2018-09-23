package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Currency;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ItemMonetaryAmountTest extends BaseEntityTest {

    @Test
    public void shouldSetGeneratedValues() {
        session.beginTransaction();

        Item item = new Item();
        String name = "item";
        item.setName(name);
        item.setAuctionEnd(Date.from(Instant.now().plus(Duration.ofDays(2))));
        item.setBuyNowPrice(new MonetaryAmount(new BigDecimal("12.33"), Currency.getInstance("USD")));
        session.persist(item);

        session.getTransaction().commit();
        session.clear();

        Item saved = session.find(Item.class, item.getId());
        assertEquals("12.33 USD", saved.getBuyNowPrice().toString());
    }
}
