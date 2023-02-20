package com.nooglers.dao;


import com.nooglers.domains.User;

import java.util.List;

class UserDaoTest {


    private static void getAllTest(UserDao userDao) {
        List<User> all = userDao.getAll();
        System.out.println(all);

    }

    private static User updateTest(UserDao userDao) {
        User jack = userDao.update(User.builder().username("jack").id(1).build());
        System.out.println(jack);
        System.out.println(jack);
        return jack;
    }

    private static void getTest(UserDao userDao) {
        User user = userDao.get(1);
        System.out.println(user);
    }

    private static void deleteTest(UserDao userDao) {
        User delete = userDao.delete(1);
        System.out.println(delete);
    }


    private static void saveTest(UserDao userDao) {
        User user = User.builder().email("email@dfsa4.com").username("polarBear").password("321").build();

        userDao.save(user);
    }

    public static void main(String[] args) {
        UserDao userDao = UserDao.get();


        saveTest(userDao);
        deleteTest(userDao);
        getTest(userDao);
        User jack = updateTest(userDao);
        getAllTest(userDao);
    }

}