package services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactory entityManagerFactory;

    private static String persistenceUnitName = "web_excel";

    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getInstance() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        }
        return entityManagerFactory;
    }
}
