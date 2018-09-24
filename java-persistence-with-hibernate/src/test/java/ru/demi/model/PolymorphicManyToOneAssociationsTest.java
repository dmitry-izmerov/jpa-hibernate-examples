package ru.demi.model;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import ru.demi.BaseEntityTest;

import static org.junit.Assert.assertThat;

public class PolymorphicManyToOneAssociationsTest extends BaseEntityTest {

    @Test
    public void shouldSetGeneratedValues() {
        session.beginTransaction();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner("owner");
        bankAccount.setAccount("account");
        bankAccount.setBankName("bank name");
        bankAccount.setSwift("swift");
        User user = new User();
        user.setDefaultBilling(bankAccount);

        session.persist(bankAccount);
        session.persist(user);

        session.getTransaction().commit();
        session.clear();

        User saved = session.find(User.class, user.getId());
        assertThat(saved.getDefaultBilling(), CoreMatchers.instanceOf(BankAccount.class));
    }
}
