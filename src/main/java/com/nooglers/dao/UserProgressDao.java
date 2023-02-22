package com.nooglers.dao;

import com.nooglers.domains.progress.UserProgress;
import lombok.Data;

import java.util.List;
@Data
public class UserProgressDao extends BaseDAO<UserProgress, Integer> {
    public List<UserProgress> findAll(Integer id , Integer moduleId) {

        final List<UserProgress> resultList = entityManager
                .createQuery("from user_progress  up where up.user.id=?1 and up.card.deleted=0 and up.card.module.id=?2" , UserProgress.class)
                .setParameter(1 , id)
                .setParameter(2 , moduleId)
                .getResultList();

        for ( UserProgress userProgress : resultList ) {
            entityManager.refresh(userProgress);
        }

        return resultList;
    }
}
