package com.nooglers.dao;

import com.nooglers.domains.User;
import com.nooglers.utils.Encrypt;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserDao extends BaseDao<User, Integer> {


    @Override
    public Integer save(User user) {
        user.setPassword(Encrypt.decodePassword(user.getPassword()));
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return user.getId();
    }


    @Override
    public User update(User user) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        User edittingUser = entityManager.find(User.class , user.getId());

        if ( user.getPassword() != null ) edittingUser.setPassword(Encrypt.decodePassword(user.getPassword()));
        if ( user.getUsername() != null ) edittingUser.setUsername(user.getUsername());
        if ( user.getEmail() != null ) edittingUser.setEmail(user.getEmail());

        transaction.commit();
        return edittingUser;
    }

    @Override
    public User delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class , id);
        user.setDeleted(( short ) 1);
        transaction.commit();
        return user;
    }

    @Override
    protected User get(Integer id) {
        return entityManager.find(User.class , id);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from Users u" , User.class).getResultList();
    }


}
