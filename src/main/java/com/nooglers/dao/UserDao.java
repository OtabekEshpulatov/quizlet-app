package com.nooglers.dao;

import com.nooglers.domains.User;
import com.nooglers.utils.Encrypt;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao extends BaseDao<User, Integer> {
    private static final ThreadLocal<UserDao> USER_DAO_THREAD_LOCAL = ThreadLocal.withInitial(UserDao::new);

    @Override

    public User save(User user) {
        user.setPassword(Encrypt.decodePassword(user.getPassword()));
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return user;
    }


    @Override
    public User update(User user) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        User edittingUser = entityManager.find(User.class, user.getId());
        if (user.getPassword() != null) edittingUser.setPassword(Encrypt.decodePassword(user.getPassword()));
        if (user.getUsername() != null) edittingUser.setUsername(user.getUsername());
        if (user.getEmail() != null) edittingUser.setEmail(user.getEmail());
        edittingUser.setUpdatedAt(LocalDateTime.now());
        transaction.commit();
        return edittingUser;
    }

    @Override
    public User delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = entityManager.find(User.class, id);
        user.setDeleted((short) 1);
        transaction.commit();
        return user;
    }

    @Override
    public User get(Integer id) {
        return entityManager.find(User.class, id);
    }

    public User get(String emailOrUsername) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<User> typedQuery = entityManager.createQuery(
                        "select u from Users u where (u.email = :text or u.username = :text) and u.deleted = 0", User.class)
                .setParameter("text", emailOrUsername);
        transaction.commit();
        List<User> userList = typedQuery.getResultList();
        return userList.stream()
                .filter(user -> user.getUsername().equals(emailOrUsername)
                        || user.getEmail().equals(emailOrUsername))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from Users u", User.class).getResultList();
    }


    public static UserDao getInstance() {
        return USER_DAO_THREAD_LOCAL.get();
    }

}
