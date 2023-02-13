package com.nooglers.dao;

import com.nooglers.domains.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class BaseDao<T> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    {
        Users bookBook = entityManager.createQuery("select b from mybook b where b.id = :id" , Users.class)
                .setParameter("id" , 9).getSingleResult();
    }

    protected abstract void save(Users users);

    protected abstract void save(T t);

    protected abstract int update(T t);

    protected abstract boolean delete(T t);

    protected abstract List<T> getAll();


}
