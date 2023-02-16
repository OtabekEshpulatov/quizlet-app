package com.nooglers.dao.test;

import com.nooglers.domains.test.Variant;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.utils.EntityProvider;
import com.nooglers.domains.Card;
import com.nooglers.domains.User;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.enums.QuizType;
import com.nooglers.utils.ApplicationUtils;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.JoinType;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static com.nooglers.utils.ApplicationUtils.*;

public class QuestionDao implements EntityProvider {


    public SolveQuestionDto generateTest(Integer userId) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        var quizHistoryBuilder = QuizHistory.builder();
        User createdBy = entityManager.find(User.class , userId);
        quizHistoryBuilder.createdBy(createdBy);

        entityManager.createQuery("update quiz_history qh set qh.deleted=cast(1 as short ) where qh.createdBy=?1 and qh.finishedAt is null").setParameter(1 , createdBy).executeUpdate();

        String selectCards = """
                select c from Card c
                            where c <> all (select up.card
                                               from user_progress up
                                               where up.user=?1)
                """;

        @SuppressWarnings( "unchecked" ) List<Card> cardsList = entityManager.createQuery(selectCards , Card.class).setParameter(1 , createdBy).getResultList();

        for ( Card card : cardsList )
            entityManager.persist(UserProgress.builder().user(createdBy).card(card).build());
        List<UserProgress> resultList = entityManager.createQuery("select up from user_progress up where up.user=?1 order by up.score " , UserProgress.class).setParameter(1 , createdBy).getResultList();

        quizHistoryBuilder.startedAt(LocalDateTime.now()).totalQuestionCount(resultList.size());

        QuizHistory quizHistory = quizHistoryBuilder.build();
        entityManager.persist(quizHistory);

//        System.out.println(resultList);

        for ( UserProgress userProgress : resultList ) {

            final Card card = userProgress.getCard();
            var questionBuilder = Question.builder().card(card).quizHistory(quizHistory);
            int randomInt = random.nextInt(3);
            String description = card.getDescription();
            final String term = card.getTitle();

            if ( randomInt == 0 ) { // true-false

                Question question = questionBuilder.definition(description).quizType(QuizType.TRUE_FALSE).build();
                final Card randomCard = resultList.get(random.nextInt(resultList.size())).getCard();

                if ( !random.nextBoolean() ) {
                    question.setDefinition(randomCard.getDescription());
                    var variant = Variant.builder().isCorrect(false).term(randomCard.getTitle()).question(question).build();
                    entityManager.persist(variant);
                }

                var variant = Variant.builder().isCorrect(true).term(term).question(question).build();
                entityManager.persist(variant);

            } else if ( randomInt == 1 ) {


                final Question question = questionBuilder.quizType(QuizType.WRITING).definition(description).build();
                Variant variant = Variant.builder().question(question).term(term).isCorrect(true).build();

                entityManager.persist(variant);

            } else { // if ( randomInt == 2 )
                questionBuilder.definition(description).quizType(QuizType.TEST).build();
                final Question question = questionBuilder.build();
                createWrongAnswers(term , question , resultList);
                final Variant correctVariant = Variant.builder().question(question).isCorrect(true).term(term).build();
                entityManager.persist(correctVariant);

            }
        }


        transaction.commit();

        return next(userId);

    }

    public SolveQuestionDto next(Integer userId) {
        try {
            final Question singleResult = entityManager.createQuery("from question q  join quiz_history  qh on qh.id=q.quizHistory.id where qh.deleted=0 and qh.createdBy.id=?1 and q.userAnswers = null" , Question.class).setParameter(1 , userId).setMaxResults(1).getSingleResult();
            final List<Variant> resultList = entityManager.createQuery("from variant v where v.question=?1" , Variant.class).setParameter(1,singleResult).getResultList();
            return new SolveQuestionDto(singleResult.getQuizType().name() , singleResult.getDefinition() , resultList,singleResult.getId());
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }

    private void createWrongAnswers(String correctTitle , Question question , List<UserProgress> resultList) {

        for ( int i = 0 ; i < resultList.size() && i < 4 ; i++ ) {
            final Card card = resultList.get(i).getCard();
            String term = card.getTitle();
            if ( !term.equals(correctTitle) ) {
                final Variant variant = Variant.builder().term(term).isCorrect(false).question(question).build();
                entityManager.persist(variant);
            }
        }
    }
}
