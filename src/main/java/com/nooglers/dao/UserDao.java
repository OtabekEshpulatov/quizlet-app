package com.nooglers.dao;

import com.nooglers.domains.User;
import com.nooglers.utils.Encrypt;
import com.nooglers.utils.EntityProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;

public class UserDao extends BaseDAO<User, Integer> implements EntityProvider {


//    @Override
//    public User save(User user) {
//        user.setPassword(Encrypt.decodePassword(user.getPassword()));
//        final EntityManager em = entityManager.get();
//
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(user);
//        transaction.commit();
//        return user;
//    }
//
//
//    @Override
//    public User update(User user) {
//        final EntityManager em = entityManager.get();
//        em.getTransaction().begin();
//        User edittingUser = em.find(User.class , user.getId());
//
//        if ( user.getPassword() != null ) edittingUser.setPassword(Encrypt.decodePassword(user.getPassword()));
//        if ( user.getUsername() != null ) edittingUser.setUsername(user.getUsername());
//        if ( user.getEmail() != null ) edittingUser.setEmail(user.getEmail());
//        edittingUser.setUpdatedAt(LocalDateTime.now());
//        em.getTransaction().commit();
//        return edittingUser;
//    }
//
//    @Override
//    public User delete(Integer id) {
//        final EntityManager em = entityManager.get();
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        User user = em.find(User.class , id);
//        user.setDeleted(( short ) 1);
//        transaction.commit();
//        return user;
//    }
//
//    @Override
//    protected User get(Integer id) {
//        return entityManager.get().find(User.class , id);
//    }
//
//    @Override
//    public List<User> getAll() {
//        return entityManager.get().createQuery("select u from Users u" , User.class).getResultList();
//    }


}
