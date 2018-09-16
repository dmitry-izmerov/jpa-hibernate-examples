package ru.demi.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Slf4j
@UtilityClass
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
		try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
            StandardServiceRegistryBuilder.destroy(registry);
			throw new RuntimeException("There was an error building the factory");
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}