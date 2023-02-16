package com.nooglers.services;

import com.nooglers.dao.test.QuestionDao;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.dto.SubmitQuestionDto;
import lombok.NonNull;

import static com.nooglers.configs.ThreadSafeBeansContainer.QUESTION_DAO;


public class QuizService {

    public static final QuestionDao dao = QUESTION_DAO.get();

    public SolveQuestionDto generateTest(@NonNull Integer userId) {
        return dao.generateTest(userId);
    }

    public SolveQuestionDto next(@NonNull Integer userId) {
        return dao.next(userId);
    }

    public static void main(String[] args) {
        QuizService quizService = new QuizService();
//        final SolveQuestionDto question = quizService.generateTest(1);
//
//        System.out.println(question);
//
//
        final SolveQuestionDto next = quizService.next(1);

        System.out.println(next);

//        final Question question = quizService.generateTest(1);
//        System.out.println(question);


    }

    public void submit(@NonNull Integer questionId , String answer) {
        dao.submit(questionId , answer);
    }

    public int questionLeft(Integer userId) {
        return dao.questionLeft(userId);
    }

    public QuizHistory finish(Integer userId) {
        return dao.finish(userId);
    }
}
