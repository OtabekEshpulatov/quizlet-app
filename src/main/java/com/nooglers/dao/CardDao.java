package com.nooglers.dao;

import com.nooglers.domains.Card;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CardDao extends BaseDAO<Card, Integer> {
    @Override
    public Card save(Card card) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(card);
        transaction.commit();
        return card;
    }

//    @Override
    public boolean update(Card card) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Card card1 = entityManager.merge(card);
        transaction.commit();
        return true;
    }

    @Override
    public boolean deleteById(Integer integer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery("update card set deleted=true where id=:id").setParameter("id" , integer).executeUpdate();
        transaction.commit();
        return true;
    }


    public List<Card> getAll() {
        return null;
    }


    public Card get(Integer cardid) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Card card = entityManager.find(Card.class , cardid);
        transaction.commit();
        return card;
    }


    protected Card getById(Class<Card> className , Integer primaryKey) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Card card = entityManager.find(Card.class , primaryKey);
        transaction.commit();
        return card;
    }

    public List<Card> getCardsByModuleId(int moduleId) {
        List<Card> res = new ArrayList<>();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<Card> cards = entityManager
                .createQuery("select c from card c where c.module.id=:id and deleted=false" , Card.class)
                .setParameter("id" , moduleId)
                .getResultList();
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
}
