package com.nooglers.dao;

import com.nooglers.domains.Card;
import com.nooglers.domains.Class;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CardDao extends BaseDAO<Card, Integer> {

    private static final ThreadLocal<CardDao> classDaoInstance = ThreadLocal.withInitial(CardDao::new);

    public static CardDao getInstance() {
        return classDaoInstance.get();
    }
//    @Override
//    public Card save(Card card) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(card);
//        transaction.commit();
//        return card;
//    }

    //    @Override
    public boolean update(Card card) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Card card1 = entityManager.merge(card);
        transaction.commit();
        return true;
    }


    public Card delete(Integer integer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        final Card byId = findById(integer);
        byId.setDeleted(( short ) 1);
        transaction.commit();
        return byId;

    }


    public List<Card> getAll() {
        return null;
    }


    public Card get(Integer cardid) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Card card = entityManager.find(Card.class , cardid);
        transaction.commit();
        return card.getDeleted() == 0 ? card : null;
    }


//    protected Card getById( className , Integer primaryKey) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        Card card = entityManager.find(Card.class , primaryKey);
//        transaction.commit();
//        return card;
//    }

    public List<Card> getCardsByModuleId(int moduleId) {
        List<Card> res = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Card> cards = entityManager.createQuery("select c from card c where c.module.id=:id and deleted=0" , Card.class).setParameter("id" , moduleId).getResultList();
        transaction.commit();

        for ( Card card : cards ) {
            Card card1 = get(card.getId());
            res.add(card1);
            entityManager.refresh(card);
        }
        return res;
    }

    public int getDocId(Integer cardid) {

        return 0;
    }

    public List<Card> getAllModuleCards(Integer moduleId) {

        return entityManager.createQuery("from card  c where c.module.id=?1 and c.deleted=0" , Card.class)
                .setParameter(1 , moduleId).getResultList();
    }
}
