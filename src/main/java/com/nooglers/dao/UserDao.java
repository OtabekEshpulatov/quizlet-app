package com.nooglers.dao;

import com.nooglers.domains.User;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserDao extends BaseDao<User> {


    @Override
    public void save(User users) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(users);
        transaction.commit();
    }


    @Override
    public void update(User o) {


        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        User user = entityManager.find(User.class , o.getId());
        if ( o.getPassword() != null ) user.setPassword(user.getPassword());
        if ( o.getUsername() != null ) user.setUsername(user.getUsername());
        transaction.commit();

    }

    @Override
    public boolean delete(User o) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
