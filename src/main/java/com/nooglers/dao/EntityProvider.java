package com.nooglers.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public interface EntityProvider {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
}
