package com.nooglers.dao;

import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;


import java.util.List;

public class QuizHistoryDao extends BaseDao<QuizHistory, Integer> {


    @Override
    public List<QuizHistory> getAll() {
        return entityManager.createQuery("select b from quiz_history b", QuizHistory.class).getResultList();
    }

    @Override
    public QuizHistory get(Integer quizhistoryId) {
      // finish bolgandan keyingi
        return entityManager.createQuery("select b from quiz_history b where id=?1",QuizHistory.class).setParameter(1,quizhistoryId).getSingleResult();

    }



    @Override
    public QuizHistory save(QuizHistory quizHistory) {
        return null;
    }

    @Override
    public QuizHistory update(QuizHistory quizHistory) {
        return null;
    }

    @Override
    public QuizHistory delete(Integer integer) {
        return null;
    }

    public List<QuizHistory> getRecentTestResults(Integer userId) {

        // eng oxirgilari

       return entityManager.createQuery("select qh from quiz_history  qh where qh.createdBy=?1 order by finishedAt desc ",QuizHistory.class).setParameter(1,userId).getResultList();
    }
}
