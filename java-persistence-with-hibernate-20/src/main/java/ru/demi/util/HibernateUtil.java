package ru.demi.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
@UtilityClass
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
            Configuration config = new Configuration().configure();
            //config.setImplicitNamingStrategy(ImplicitNamingStrategyImpl.INSTANCE);
            //config.setPhysicalNamingStrategy(PhysicalNamingStrategyImpl.INSTANCE);
            return config.buildSessionFactory();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("There was an error building the factory");
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}