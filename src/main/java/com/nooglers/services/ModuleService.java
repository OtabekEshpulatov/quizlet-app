package com.nooglers.services;

import com.nooglers.dao.CardDao;
import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Module;
import com.nooglers.domains.progress.UserProgress;

import java.util.List;

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
        final List<Card> cards = up.stream()
                .map(UserProgress::getCard)
                .filter(card -> card.getDeleted() == 0)
                .toList();

        return cards;
    }
}
