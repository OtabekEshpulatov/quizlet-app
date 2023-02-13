package com.nooglers;

import com.nooglers.dao.UserDao;
import com.nooglers.domains.Users;

public class Main {
    public static void main(String[] args) {

        UserDao dao=new UserDao();

        Users users = Users.builder()
                .email("email@gmail.com")
                .password("123")
                .username("someName")
                .build();

        dao.save(users);



    }
}
