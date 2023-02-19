package com.nooglers.services.userprogress;

import com.nooglers.dao.UserProgressDao;
import com.nooglers.domains.progress.UserProgress;

import java.util.List;

public class UserProgressService {


    UserProgressDao dao = new UserProgressDao();


    public List<UserProgress> getUserProgress(Integer id) {
        return dao.findAll(id);
    }
}
