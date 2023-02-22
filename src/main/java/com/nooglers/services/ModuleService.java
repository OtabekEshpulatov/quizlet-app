package com.nooglers.services;

import com.nooglers.dao.CardDao;
import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Module;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.dto.ModuleSetDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModuleService {

    private final ModuleDao dao = new ModuleDao();
    private final CardDao cardDao = new CardDao();

    public Module getById(Integer id) {
        return dao.findById(id);
    }

    public List<Card> getCards(Integer moduleId) {
        return cardDao.getAllModuleCards(moduleId);
    }

    public void updateLastSeend(Module module) {
        dao.updateLastSeen(module);
    }

    public List<Card> extractCards(List<UserProgress> up) {
        final List<Card> cards = up.stream().map(UserProgress::getCard).filter(card -> card.getDeleted() == 0).toList();


        return cards;
    }

    public Set<Module> getModulesByFolderId(Integer folderId) {
        return dao.getModulesByFolderId(folderId);
    }

    public List<ModuleSetDto> getModules(Integer userId , Integer folderId) {

        final List<Module> modules = dao.getModules(userId , ( short ) 0);
        final List<ModuleSetDto> moduleSetDtos = new ArrayList<>();


        for ( Module module : modules ) {
            final ModuleSetDto moduleSetDto = new ModuleSetDto(dao.isIncludedInFolder(module.getId() , folderId) , module);
            moduleSetDtos.add(moduleSetDto);
        }

        return moduleSetDtos;
    }


}
