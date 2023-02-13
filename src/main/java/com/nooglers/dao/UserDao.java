package com.nooglers.dao;

import com.nooglers.domains.Users;

import java.util.List;

public class UserDao extends BaseDao<Users> {


    @Override
    public void save(Users users) {
        entityManager.persist(users);
    }


    @Override
    public int update(Users o) {
        return 0;
    }

    @Override
    public boolean delete(Users o) {
        return false;
    }

    @Override
    public List<Users> getAll() {
        return null;
    }
}
