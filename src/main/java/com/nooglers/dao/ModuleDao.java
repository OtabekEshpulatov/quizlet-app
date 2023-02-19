package com.nooglers.dao;

import com.nooglers.domains.Module;

import java.util.List;

public class ModuleDao extends BaseDAO<Module, Integer> {

    private static final ThreadLocal<ModuleDao> moduleDaoThreadLocal =
            ThreadLocal.withInitial(ModuleDao::new);

    public static ModuleDao getInstance() {
        return moduleDaoThreadLocal.get();
    }

    @Override
    public boolean deleteById(Integer id) {

        entityManager.getTransaction().begin();
        entityManager.createQuery("update Module  m set m.deleted=cast(1 as short )where m.id=?1")
                .setParameter(1 , id).executeUpdate()   ;
        entityManager.getTransaction().commit();
        return true;

    }

    public List<Module> getAll(Integer userId) {
        return entityManager.createQuery("from Module  m where m.createdBy.id=?1 and m.deleted=0 order by m.createdAt desc" , Module.class).setParameter(1 , userId).getResultList();
    }


    //    @Override
//    public Module save(Module module) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(module);
//        transaction.commit();
//        return module;
//    }
//
//    public Module getModuleById(int modulId) {
//        TypedQuery<Module> query = entityManager
//                .createQuery("select m from Module m where m.deleted = 0  and m.id = ?1" , Module.class);
//        Module module = ( Module ) query.setParameter(1 , modulId).getSingleResult();
//        return module;
//    }
//
//    @Override
//    public Module update(Module module) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        Module edittingModule = entityManager.find(Module.class , module.getId());
//
//        if ( module.getName() != null ) edittingModule.setName(module.getName());
//        if ( module.getDescription() != null ) edittingModule.setDescription(module.getDescription());
//        if ( !module.isPublic() ) edittingModule.setPublic(false);
//        edittingModule.setUpdatedAt(LocalDateTime.now());
//        transaction.commit();
//        return edittingModule;
//    }
//
//    @Override
//    public boolean delete(Integer id) {
//        begin();
//        Module module = entityManager.find(Module.class , id);
//        module.setDeleted(( short ) 1);
//       commit();
//        return module;
//    }
//
//    @Override
//    public List<Module> getAll() {
////        return entityManager.createQuery("select u from Users u" , User.class).getResultList();
//        return entityManager.createQuery("select m from Module  m" , Module.class).getResultList();
//    }
//
//    @Override
//    public Module get(Integer integer) {
//        return null;
//    }
}
