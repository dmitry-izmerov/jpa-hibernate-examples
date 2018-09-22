package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import static org.junit.Assert.assertNotNull;

public class UserEmbeddedAddressesTest extends BaseEntityTest {

    @Test
    public void shouldSetGeneratedValues() {
        session.beginTransaction();

        Address homeAddress = new Address("RF", "Kransodarskiy krai", "Sochi", "Lenina", "100100");
        Address billingAddress = new Address("RF", "Moskovskaya oblast", "Zagorsk", "Ribnaya", "141000");

        User user = new User();
        user.setHomeAddress(homeAddress);
        user.setBillingAddress(billingAddress);

        session.persist(user);

        session.getTransaction().commit();
        session.clear();

        User saved = session.find(User.class, user.getId());
        Address savedHomeAddress = saved.getHomeAddress();
        Address savedBillingAddress = saved.getBillingAddress();

        assertNotNull(savedHomeAddress.getCountry());
        assertNotNull(savedHomeAddress.getRegion());
        assertNotNull(savedHomeAddress.getCity());
        assertNotNull(savedHomeAddress.getStreet());
        assertNotNull(savedHomeAddress.getZipCode());

        assertNotNull(savedBillingAddress.getCountry());
        assertNotNull(savedBillingAddress.getRegion());
        assertNotNull(savedBillingAddress.getCity());
        assertNotNull(savedBillingAddress.getStreet());
        assertNotNull(savedBillingAddress.getZipCode());
    }
}
