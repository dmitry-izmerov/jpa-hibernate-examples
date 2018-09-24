package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import static org.junit.Assert.assertEquals;

public class PolymorphicCollectionsTest extends BaseEntityTest {

    @Test
    public void shouldSetGeneratedValues() {
        session.beginTransaction();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner("owner");
        bankAccount.setAccount("account");
        bankAccount.setBankName("bank name");
        bankAccount.setSwift("swift");

        CreditCard creditCard = new CreditCard();
        creditCard.setOwner("owner");
        creditCard.setCardNumber("00201");
        creditCard.setExpMonth("september");
        creditCard.setExpYear("2020");

        User user = new User();
        user.addBillingDetails(bankAccount);
        user.addBillingDetails(creditCard);

        session.persist(bankAccount);
        session.persist(creditCard);
        session.persist(user);

        session.getTransaction().commit();
        session.clear();

        User saved = session.find(User.class, user.getId());
        assertEquals(2, saved.getBillingDetails().size());
    }
}
