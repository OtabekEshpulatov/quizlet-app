package com.nooglers;

import com.nooglers.dao.UserDao;
import com.nooglers.domains.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        final User build = User.builder().build();
        UserDao.getInstance().save(build);
        List<Date>dates=new ArrayList<>();
//        dates.re
    }
    private String gr(Integer n, Function<Integer,String> f){
        return f.apply(n);
    }
}
