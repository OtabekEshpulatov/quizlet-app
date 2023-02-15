package com.nooglers.dao.dao;

import com.nooglers.domains.User;

public class UserDaoTest {
    public static void main(String[] args) {


//        saveTest();
//        updateTest();

    }

    private static void updateTest() {
        com.nooglers.dao.UserDao dao = new com.nooglers.dao.UserDao();

        User polarBear = dao.update(User.builder()
                .username("polarBear")
                .id(2)
                .build());

        System.out.println(polarBear);
    }

    private static void saveTest() {
        com.nooglers.dao.UserDao dao = new com.nooglers.dao.UserDao();


        User user = dao.save(User.builder()
                .username("polar")
                .email("polar@gmail.com")
                .password("123")
                .build());

        dao.save(user);
    }


}
