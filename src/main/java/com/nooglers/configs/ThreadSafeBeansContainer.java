package com.nooglers.configs;

import com.nooglers.services.UserService;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);

}
