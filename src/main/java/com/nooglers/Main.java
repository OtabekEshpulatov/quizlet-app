package com.nooglers;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.CardDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import com.nooglers.domains.test.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        UserDao dao = new UserDao();
//
//        User users = User.builder().email("email@gmail.com").password("123").username("someName").build();
//
//        dao.save(users);

//        User name = User.builder()
//                .id(1)
//                .username("otash")
//                .password("8182040615")
//                .email("otabekeshpulatov123@gmail.com")
//                .build();


//        dao.update(name);
    }
}
