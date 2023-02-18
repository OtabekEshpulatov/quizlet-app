package com.nooglers.configs;

import com.nooglers.services.ModuleService;
import com.nooglers.services.UserService;

public class ThreadSafeBeansContainer {


    public static final ThreadLocal<UserService> USER_SERVICE = ThreadLocal.withInitial(UserService::new);
    public static final ThreadLocal<ModuleService> MODULE_SERVICE = ThreadLocal.withInitial(ModuleService::new);

}
