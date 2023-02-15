package com.nooglers.services;

import com.nooglers.dao.EntityProvider;
import com.nooglers.dao.test.QuestionDao;
import com.nooglers.domains.test.Question;
import lombok.NonNull;

import java.util.List;

import static com.nooglers.configs.ThreadSafeBeansContainer.QUESTION_DAO;


public class QuizService {

    public static final QuestionDao dao = QUESTION_DAO.get();

    public List<Question> generateTest(@NonNull Integer userId) {

     return dao.generateTest(userId);

    }
}
