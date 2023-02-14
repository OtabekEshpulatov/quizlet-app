package com.nooglers.configs;

import com.nooglers.services.UserService;

import java.util.function.Supplier;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);

}
