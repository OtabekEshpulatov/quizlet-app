package com.nooglers.dao;

import com.nooglers.domains.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class BaseDao<T, ID> {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    protected static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    protected static void begin() {
        entityManager.getTransaction().begin();
    }
    protected static void commit() {
        entityManager.getTransaction().commit();
    }


    protected abstract T save(T t);


    protected abstract T update(T t);

    protected abstract T delete(ID id);

    protected abstract List<T> getAll();

    protected abstract T get(ID id);


}
