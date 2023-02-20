package com.nooglers.configs;

import com.nooglers.dao.CardDao;
import com.nooglers.dao.DocumentDao;
import com.nooglers.dao.test.QuestionDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Document;
import com.nooglers.services.ClassService;
import com.nooglers.services.ModuleService;
import com.nooglers.services.QuizService;
import com.nooglers.services.UserService;
import com.nooglers.services.userprogress.UserProgressService;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);
    public static final ThreadLocal<ModuleService> MODULE_SERVICE = ThreadLocal.withInitial(ModuleService::new);

    public static final ThreadLocal<QuizService> QUIZ_SERVICE = ThreadLocal.withInitial(QuizService::new);

    public static final ThreadLocal<ClassService> CLASS_SERVICE = ThreadLocal.withInitial(ClassService::new);

    public static final ThreadLocal<UserProgressService> USER_PROGRESS_SERVICE = ThreadLocal.withInitial(UserProgressService::new);
    public static final ConcurrentHashMap<Integer, String> COOKIE_VALUES = new ConcurrentHashMap<>();
    public static final ThreadLocal<CardDao> CARD_DAO = ThreadLocal.withInitial(CardDao::new);
    public static final ThreadLocal<DocumentDao> DOCUMENT_DAO = ThreadLocal.withInitial(DocumentDao::new);
}
