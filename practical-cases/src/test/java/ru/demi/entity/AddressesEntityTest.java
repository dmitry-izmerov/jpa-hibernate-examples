package ru.demi.entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import ru.demi.BaseEntityTest;

import static org.junit.Assert.assertNotNull;

public class AddressesEntityTest extends BaseEntityTest {

    @Test
    public void shouldSaveAddresses() {
        session.beginTransaction();

        ActualAddress actualAddress = new ActualAddress();
        PostalAddress postalAddress = new PostalAddress();
        RegistrationAddress registrationAddress = new RegistrationAddress();

        session.persist(fillAddress(actualAddress));
        session.persist(fillAddress(postalAddress));
        session.persist(fillAddress(registrationAddress));

        session.getTransaction().commit();
        session.clear();

        checkAddress(session.find(ActualAddress.class, actualAddress.getId()));
        checkAddress(session.find(PostalAddress.class, postalAddress.getId()));
        checkAddress(session.find(RegistrationAddress.class, registrationAddress.getId()));
    }

    private Address fillAddress(Address address) {
        return address
            .setZipCode(RandomStringUtils.randomNumeric(6))
            .setCountry(RandomStringUtils.randomAlphabetic(8))
            .setArea(RandomStringUtils.randomAlphabetic(8))
            .setDistrict(RandomStringUtils.randomAlphabetic(8))
            .setPlace(RandomStringUtils.randomAlphabetic(8))
            .setStreet(RandomStringUtils.randomAlphabetic(8))
            .setHouse(RandomStringUtils.randomAlphanumeric(6))
            .setBuilding(RandomStringUtils.randomAlphabetic(8))
            .setFlat(RandomStringUtils.randomAlphanumeric(5))
            .setRegion(RandomStringUtils.randomAlphabetic(8))
            .setFullAddress(RandomStringUtils.randomAlphabetic(8))
            .setCity(RandomStringUtils.randomAlphabetic(8))
            .setSubject(RandomStringUtils.randomAlphabetic(8));
    }

    private void checkAddress(Address address) {
        assertNotNull(address.getId());
        assertNotNull(address.getZipCode());
        assertNotNull(address.getArea());
        assertNotNull(address.getDistrict());
        assertNotNull(address.getPlace());
        assertNotNull(address.getStreet());
        assertNotNull(address.getHouse());
        assertNotNull(address.getBuilding());
        assertNotNull(address.getFlat());
        assertNotNull(address.getRegion());
        assertNotNull(address.getFullAddress());
        assertNotNull(address.getCity());
        assertNotNull(address.getSubject());
    }
}
