package ru.demi.model;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import static org.junit.Assert.assertNotNull;

public class TablePerEachClassOfHierarchyWithJoinsTest extends BaseEntityTest {

    @Test
    public void shouldSaveBankAccount() {
        session.beginTransaction();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner("owner");
        bankAccount.setAccount("account");
        bankAccount.setBankName("bank name");
        bankAccount.setSwift("swift");
        session.persist(bankAccount);

        session.getTransaction().commit();
        session.clear();

        BankAccount saved = session.find(BankAccount.class, bankAccount.getId());
        assertNotNull(saved.getId());
        assertNotNull(saved.getOwner());
        assertNotNull(saved.getAccount());
        assertNotNull(saved.getBankName());
        assertNotNull(saved.getSwift());
    }

    @Test
    public void shouldSaveCreditCard() {
        session.beginTransaction();

        CreditCard creditCard = new CreditCard();
        creditCard.setOwner("owner");
        creditCard.setCardNumber("00201");
        creditCard.setExpMonth("september");
        creditCard.setExpYear("2020");
        session.persist(creditCard);

        session.getTransaction().commit();
        session.clear();

        CreditCard saved = session.find(CreditCard.class, creditCard.getId());
        assertNotNull(saved.getId());
        assertNotNull(saved.getOwner());
        assertNotNull(saved.getCardNumber());
        assertNotNull(saved.getExpMonth());
        assertNotNull(saved.getExpYear());
    }
}
