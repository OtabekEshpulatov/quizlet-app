package com.nooglers.dao;

import com.nooglers.domains.Class;
import com.nooglers.domains.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ClassDao extends BaseDao<Class, Integer> {
    private static final ThreadLocal<ClassDao> CLASS_DAO_THREAD_LOCAL = ThreadLocal.withInitial(ClassDao::new);

    @Override
    public Class save(Class aClass) {
        begin();
        entityManager.persist(aClass);
        commit();
        return aClass;
    }


    @Override
    public Class update(Class aClass) {
        begin();
        entityManager.merge(aClass);
        commit();
        return aClass;
    }

    public Class update(Class aClass, User user) {
        begin();
        aClass.setUpdatedAt(LocalDateTime.now());
        aClass.getUsers().add(user);
        entityManager.persist(aClass);
        entityManager.persist(user);
        commit();
        return aClass;
    }

    @Override
    public Class delete(Integer id) {
        begin();
        Class aClass = entityManager.find(Class.class, id);
        aClass.setDeleted((short) 1);
        commit();
        return aClass;
    }

    @Override
    public Class get(Integer id) {
        return entityManager.find(Class.class, id);
    }

    public List<Class> getAll(Integer userId) {
//        begin();
//        TypedQuery<Class> typedQuery = entityManager.createQuery(
//                        "select c from Class c where c.deleted = 0 and (c.permissionToInvite and (c.users = :id or c.createdBy=id)) "
//                        , Class.class)
//                .setParameter("id", userId);
//        List<Class> classList = typedQuery.getResultList();
//        entityManager.createNativeQuery("");
//        commit();
        return getAll().stream()
                .filter(aClass -> aClass.getDeleted() == 0 &&
                        ((aClass.isPermissionToInvite() && containsUser(aClass.getUsers(), userId))
                                || aClass.getCreatedBy().equals(userId)))
                .toList();
    }

    @Override
    public List<Class> getAll() {
        return entityManager.createQuery("select u from Class u where u.deleted = 0", Class.class).getResultList();
    }

//    public List<Class> getAll(Integer userId) {
//        return entityManager.createQuery("select u from Class u where u.deleted = 0 and u.createdBy = :userId", Class.class)
//                .setParameter("userId", userId)
//                .getResultList();
//    }

    private boolean containsUser(Set<User> users, Integer userId) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getId().equals(userId))
                .findAny();
        return optionalUser.isPresent();
    }

    public static ClassDao getInstance() {
        return CLASS_DAO_THREAD_LOCAL.get();
    }
}
