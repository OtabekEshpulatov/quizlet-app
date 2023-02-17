package com.nooglers.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public interface EntityProvider {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    ThreadLocal<EntityManager> entityManager = ThreadLocal.withInitial(entityManagerFactory::createEntityManager);
}
