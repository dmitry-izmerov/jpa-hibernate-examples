package ru.demi.entity;

import org.junit.Test;
import ru.demi.BaseEntityTest;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrganizationEntityTest extends BaseEntityTest {

    @Test
    public void shouldSaveOrganizationWithSubDivision() {
        session.beginTransaction();

        SubDivision subDivision = new SubDivision();
        subDivision.setFullName("sub division name");

        Organization organization = new Organization()
            .setFullName("organization name")
            .setSubDivisions(Collections.singletonList(subDivision));

        subDivision.setOrganization(organization);

        session.persist(organization);

        session.getTransaction().commit();
        session.clear();

        Organization saved = session.find(Organization.class, organization.getId());
        assertNotNull(saved.getFullName());
        assertEquals(1, saved.getSubDivisions().size());
    }
}
