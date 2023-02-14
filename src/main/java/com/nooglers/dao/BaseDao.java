package com.nooglers.dao;

import com.nooglers.domains.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class BaseDao<T, ID> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    protected abstract ID save(T t);


    protected abstract T update(T t);

    protected abstract T delete(ID id);

    protected abstract List<T> getAll();

    protected abstract T get(ID id);


}
