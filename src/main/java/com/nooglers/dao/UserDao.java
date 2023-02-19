package com.nooglers.dao;

import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class UserDao extends BaseDAO<User, Integer> {
    private static final ThreadLocal<UserDao> USER_DAO_THREAD_LOCAL = ThreadLocal.withInitial(UserDao::new);

//    @Override
//
//    public User save(User user) {
//        user.setPassword(Encrypt.decodePassword(user.getPassword()));
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(user);
//        transaction.commit();
//        return user;
//    }


    public User get(String emailOrUsername) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<User> typedQuery = entityManager.createQuery("select u from Users u where (u.email = :text or u.username = :text) and u.deleted = 0" , User.class).setParameter("text" , emailOrUsername);
        transaction.commit();
        List<User> userList = typedQuery.getResultList();
        return userList.stream().filter(user -> user.getUsername().equals(emailOrUsername) || user.getEmail().equals(emailOrUsername)).findAny().orElse(null);
    }


    public Set<User> getAllById(Integer groupId) {

        final Class singleResult = entityManager.createQuery("from Class c where c.id=?1" , Class.class)
                .setParameter(1 , groupId)
                .getSingleResult();

        return singleResult.getUsers();
    }


    public static UserDao getInstance() {
        return USER_DAO_THREAD_LOCAL.get();
    }


    public List<User> getAllByUserName(String username) {
        username = "%" + username + "%";
        final List<User> uname = entityManager.createQuery("from Users u where u.username ilike ?1 " , User.class)
                .setParameter(1 , username).getResultList();

        return uname;
    }

    public List<User> getAll() {
        return entityManager.createQuery("from Users" , User.class).getResultList();
    }


}
