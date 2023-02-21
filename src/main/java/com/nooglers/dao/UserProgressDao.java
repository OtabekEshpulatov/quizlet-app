package com.nooglers.dao;

import com.nooglers.domains.progress.UserProgress;

import java.util.List;

public class UserProgressDao extends BaseDAO<UserProgress, Integer> {
    public List<UserProgress> findAll(Integer id) {
        final List<UserProgress> resultList = entityManager.createQuery("from user_progress  up where up.user.id=?1 and up.card.deleted=0" , UserProgress.class)
                .setParameter(1 , id)
                .getResultList();

        for ( UserProgress userProgress : resultList ) {
            entityManager.refresh(userProgress);
        }

        return resultList;
    }
}
