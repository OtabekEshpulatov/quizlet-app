package com.nooglers;

import com.nooglers.dao.UserDao;
import com.nooglers.domains.User;

public class Main {
    public static void main(String[] args) {

        final User build = User.builder().build();
        UserDao.getInstance().save(build);
    }
}
