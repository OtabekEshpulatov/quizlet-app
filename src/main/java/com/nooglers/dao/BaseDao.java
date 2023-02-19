package com.nooglers.dao;

import com.nooglers.domains.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class BaseDao<T, ID> {
    protected final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    protected final EntityManager entityManager = entityManagerFactory.createEntityManager();

    protected abstract T save(T t);

    protected abstract T update(T t);

    protected abstract T delete(ID id);

    protected abstract List<T> getAll();

    protected abstract T get(ID id);
    protected T getById(Class<T> className, ID primaryKey){
        return null;
    }
}