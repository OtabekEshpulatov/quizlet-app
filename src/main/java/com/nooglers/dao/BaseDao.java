package com.nooglers.dao;

import com.nooglers.domains.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class BaseDao<T> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();






    protected abstract void save(User users);

    protected abstract void save(T t);

    protected abstract void update(T t);

    protected abstract boolean delete(T t);

    protected abstract List<T> getAll();


}
