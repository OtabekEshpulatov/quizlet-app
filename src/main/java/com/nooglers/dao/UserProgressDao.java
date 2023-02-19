package com.nooglers.dao;

import com.nooglers.domains.progress.UserProgress;

import java.util.List;

public class UserProgressDao extends BaseDAO<UserProgress, Integer> {
    public List<UserProgress> findAll(Integer id) {
        return entityManager.createQuery("from user_progress  up where up.user.id=?1" , UserProgress.class)
                .setParameter(1 , id)
                .getResultList();
    }
}
