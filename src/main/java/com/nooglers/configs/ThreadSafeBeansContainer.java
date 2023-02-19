package com.nooglers.configs;

import com.nooglers.dao.ModuleDao;
import com.nooglers.dao.test.QuestionDao;
import com.nooglers.domains.User;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.services.ModuleService;
import com.nooglers.services.QuizService;
import com.nooglers.services.UserService;
import com.nooglers.services.userprogress.UserProgressService;
import jakarta.el.BeanELResolver;
import jakarta.persistence.EntityManager;

import java.util.function.Supplier;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);
    public static final ThreadLocal<ModuleService> MODULE_SERVICE = ThreadLocal.withInitial(ModuleService::new);

    public static final ThreadLocal<QuizService> QUIZ_SERVICE = ThreadLocal.withInitial(QuizService::new);

    public static final ThreadLocal<QuestionDao> QUESTION_DAO = ThreadLocal.withInitial(QuestionDao::new);

    public static final ThreadLocal<UserProgressService> USER_PROGRESS_SERVICE = ThreadLocal.withInitial(UserProgressService::new);
}
