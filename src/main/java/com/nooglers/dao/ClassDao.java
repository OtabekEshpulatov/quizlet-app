package com.nooglers.dao;

import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class ClassDao extends BaseDAO<Class, Integer> {
    private static final ThreadLocal<ClassDao> CLASS_DAO_THREAD_LOCAL = ThreadLocal.withInitial(ClassDao::new);

    @Override
    public Class save(Class aClass) {
        begin();
        entityManager.persist(aClass);
        commit();
        return aClass;
    }


//    @Override
//    public boolean update(Class aClass) {
//        begin();
//        aClass.setUpdatedAt(LocalDateTime.now());
//        entityManager.merge(aClass);
//        commit();
//        return true;
//    }

    @Override
    public boolean deleteById(Integer id) {
        begin();
        Class aClass = entityManager.find(Class.class, id);
        aClass.setDeleted((short) 1);
        commit();
        return true;
    }


    public List<Class> search(String schoolOrClassName) {
//        begin();
//        TypedQuery<Class> typedQuery = entityManager.createQuery(
//                        "select c from Class c where (c.name ilike %:text% or c.schoolName ilike %:text%) and c.deleted = 0", Class.class)
//                .setParameter("text", schoolOrClassName);
//        List<Class> classList = typedQuery.getResultList();
//        commit();

        return getAll().stream().filter(aClass -> aClass.getName().contains(schoolOrClassName) || aClass.getSchoolName().contains(schoolOrClassName)).toList();
    }


    public List<Class> getAll() {
        return entityManager.createQuery("select u from Class u where u.deleted = 0", Class.class).getResultList();
    }

    //    public List<Class> getAll(Integer userId) {
//        return entityManager.createQuery("select u from Class u where u.deleted = 0 and u.createdBy = :userId order by createdAt desc" , Class.class).setParameter("userId" , userId).getResultList();
//
//    }
    public List<Class> getAll(Integer userId) {
        return getAll().stream()
                .filter(aClass -> aClass.getDeleted() == 0 &&
                        (containsUser(aClass.getUsers(), userId) || aClass.getCreatedBy().equals(userId)))
                .toList();
    }

    public static ClassDao getInstance() {
        return CLASS_DAO_THREAD_LOCAL.get();
    }

    public Class get(Integer groupId) {
        return entityManager.createQuery("from Class  c where c.id=?1", Class.class).setParameter(1, groupId).getSingleResult();
    }

    public boolean delete(Class group) {
        entityManager.getTransaction().begin();
        group.setDeleted((short) 1);
        entityManager.getTransaction().commit();
        return true;

//        return entityManager.createQuery("update Class  c set c.deleted=cast(1 as short ) where c.id=?1")
//                       .setParameter(1 , groupId).executeUpdate() != 0;
    }

    //@Transactional
//    public void addMember(Integer userId, Integer classId, UserDao userDao) {
//        begin();
//        User userById = userDao.findById(userId);
//        Class classById = findById(classId);
//        classById.getUsers().add(userById);
////        entityManager.persist(userById);
//        entityManager.persist(classById);
//        commit();
//    }

    private boolean containsUser(Set<User> users, Integer userId) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getId().equals(userId))
                .findAny();
        return optionalUser.isPresent();
    }

}
