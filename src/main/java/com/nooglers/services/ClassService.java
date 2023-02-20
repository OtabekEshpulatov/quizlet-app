package com.nooglers.services;

import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;

public class ClassService {
    private final ClassDao dao = new ClassDao();

    public Class getGroup(Integer groupId) {
        final Class aClass = dao.findById(groupId);
        return aClass;
    }
}
