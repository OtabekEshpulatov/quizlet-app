package com.nooglers.dao;

import java.time.LocalDateTime;
import java.util.List;

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
        aClass.setUpdatedAt(LocalDateTime.now());
        entityManager.merge(aClass);
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

    public List<Class> search(String schoolOrClassName) {
//        begin();
//        TypedQuery<Class> typedQuery = entityManager.createQuery(
//                        "select c from Class c where (c.name ilike %:text% or c.schoolName ilike %:text%) and c.deleted = 0", Class.class)
//                .setParameter("text", schoolOrClassName);
//        List<Class> classList = typedQuery.getResultList();
//        commit();

        return getAll().stream()
                .filter(aClass -> aClass.getName().contains(schoolOrClassName)
                        || aClass.getSchoolName().contains(schoolOrClassName))
                .toList();
    }

    @Override
    public List<Class> getAll() {
        return entityManager.createQuery("select u from Class u where u.deleted = 0", Class.class).getResultList();
    }

    public List<Class> getAll(Integer userId) {
        return entityManager.createQuery("select u from Class u where u.deleted = 0 and u.createdBy = :userId", Class.class)
                .setParameter("userId", userId)
                .getResultList();

    }

    public static ClassDao getInstance() {
        return CLASS_DAO_THREAD_LOCAL.get();
    }
}
