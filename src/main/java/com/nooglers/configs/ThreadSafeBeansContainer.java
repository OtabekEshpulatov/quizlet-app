package com.nooglers.configs;

import com.nooglers.services.UserService;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);
    public static final ConcurrentHashMap<Integer, String> COOKIE_VALUES = new ConcurrentHashMap<>();
}
