package com.nooglers.configs;

import com.nooglers.dao.CardDao;
import com.nooglers.dao.DocumentDao;
import com.nooglers.services.UserService;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);
    public static final ThreadLocal<CardDao> cardDao = ThreadLocal.withInitial(CardDao::new);
    public static final ThreadLocal<DocumentDao> documentDao = ThreadLocal.withInitial(DocumentDao::new);

}
