package com.nooglers.services;

import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Module;

public class ModuleService {

    private final ModuleDao dao = new ModuleDao();

    public Module getById(Integer id) {
        return dao.findById(id);
    }
}
