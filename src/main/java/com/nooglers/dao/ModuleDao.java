package com.nooglers.dao;

import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import com.nooglers.utils.Encrypt;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

public class ModuleDao extends BaseDao<Module, Integer>{

    private static final ThreadLocal<ModuleDao> moduleDaoThreadLocal =
            ThreadLocal.withInitial(ModuleDao::new);

    public static ModuleDao getInstance() {
        return moduleDaoThreadLocal.get();
    }
    @Override
    public Module save(Module module) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(module);
        transaction.commit();
        return module;
    }

    public Module getModuleById(int modulId){
        TypedQuery<Module> query = entityManager
                .createQuery("select m from Module m where m.deleted = 0  and m.id = ?1", Module.class);
        Module module = (Module) query.setParameter(1,modulId).getSingleResult();
        return module;
    }

    @Override
    public Module update(Module module) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Module edittingModule = entityManager.find(Module.class, module.getId());

        if (module.getName() != null) edittingModule.setName(module.getName());
        if (module.getDescription() != null) edittingModule.setDescription(module.getDescription());
        if (!module.isPublic()) edittingModule.setPublic(false);
        edittingModule.setUpdatedAt(LocalDateTime.now());
        transaction.commit();
        return edittingModule;
    }

    @Override
    public Module delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Module module = entityManager.find(Module.class , id);
        module.setDeleted(( short ) 1);
        transaction.commit();
        return module;
    }

    @Override
    public List<Module> getAll() {
//        return entityManager.createQuery("select u from Users u" , User.class).getResultList();
        return entityManager.createQuery("select m from Module  m", Module.class).getResultList();
    }

    @Override
    public Module get(Integer integer) {
        return null;
    }
}
