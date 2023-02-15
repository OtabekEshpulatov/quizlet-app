package com.nooglers.configs;

import com.nooglers.dao.test.QuestionDao;
import com.nooglers.services.QuizService;
import com.nooglers.services.UserService;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);
    public static final ThreadLocal<QuizService> QUIZ_SERVICE = ThreadLocal.withInitial(QuizService::new);


    public static final ThreadLocal<QuestionDao> QUESTION_DAO = ThreadLocal.withInitial(QuestionDao::new);

}
