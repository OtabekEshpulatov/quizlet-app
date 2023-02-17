package com.nooglers.dao.test;

import com.nooglers.domains.test.Variant;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.utils.ApplicationUtils;
import com.nooglers.utils.EntityProvider;
import com.nooglers.domains.Card;
import com.nooglers.domains.User;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.enums.QuizType;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.nooglers.utils.ApplicationUtils.*;

public class QuestionDao implements EntityProvider {


    public SolveQuestionDto generateTest(Integer userId) {

        if ( !entityManager.getTransaction().isActive() ) entityManager.getTransaction().begin();


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
        List<UserProgress> resultList = entityManager.createQuery("select up from user_progress up where up.user=?1 order by up.score" , UserProgress.class).setParameter(1 , createdBy).setMaxResults(ApplicationUtils.MAX_QUESTION_COUNT).getResultList();

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
                final Card randomCard = getRandomCard(card , resultList);

                if ( !random.nextBoolean() ) {
//                    question.setDefinition(randomCard.getDescription());
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
                final List<Variant> wrongAnswers = createWrongAnswers(term , question , resultList);
                wrongAnswers.add(Variant.builder().question(question).isCorrect(true).term(term).build());
                Collections.shuffle(wrongAnswers);
                addAll(wrongAnswers);
            }
        }


        entityManager.getTransaction().commit();

        return next(userId);

    }

    private void addAll(Collection<Variant> wrongAnswers) {
        for ( Variant wrongAnswer : wrongAnswers )
            entityManager.persist(wrongAnswer);

    }

    private Card getRandomCard(Card currentCard , List<UserProgress> resultList) {
        Collections.shuffle(resultList);
        return resultList.stream().filter(up -> up.getCard() != currentCard).findAny().get().getCard();
    }

    public SolveQuestionDto next(Integer userId) {
        try {
            final Question question = entityManager.createQuery("from question q  join quiz_history  qh on qh.id=q.quizHistory.id where qh.deleted=0 and qh.createdBy.id=?1 and q.userAnswer = null" , Question.class).setParameter(1 , userId).setMaxResults(1).getSingleResult();
            return new SolveQuestionDto(question.getQuizType().name() , question.getDefinition() , variants(question.getId()) , question.getId() , question.getQuizHistory().getTotalQuestionCount() , questionLeft(userId));
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }


    private List<Variant> createWrongAnswers(String correctTitle , Question question , List<UserProgress> resultList) {

        List<Variant> wrongAnswers = new ArrayList<>();

        for ( int i = 0 ; i < resultList.size() && i < 4 ; i++ ) {
            final Card card = resultList.get(i).getCard();
            String term = card.getTitle();
            if ( !term.equals(correctTitle) ) {
                final Variant variant = Variant.builder().term(term).isCorrect(false).question(question).build();
                wrongAnswers.add(variant);
            }
        }

        return wrongAnswers;
    }

    public void submit(Integer questionId , String answer) {
        entityManager.getTransaction().begin();
        final int i = entityManager.createQuery("update question q set q.userAnswer=?1 where q.id=?2").setParameter(1 , answer).setParameter(2 , questionId).executeUpdate();
        System.out.println("updated= " + i);
        entityManager.getTransaction().commit();
    }

    public int questionLeft(Integer userId) {
        try {
            return entityManager.createQuery("select count(*) from question q where q.quizHistory.createdBy.id=?1 and q.userAnswer=null and quizHistory.deleted=0" , Long.class).setParameter(1 , userId).getSingleResult().intValue();
        } catch ( NoResultException ex ) {
            return 0;
        }
    }

    @Transactional()
    public QuizHistory finish(Integer userId) {

        entityManager.getTransaction().begin();

        final List<Question> resultList = entityManager.createQuery("from question q where q.quizHistory.createdBy.id=?1 and q.quizHistory.finishedAt =null" , Question.class).setParameter(1 , userId).getResultList();
        final QuizHistory quizHistory = resultList.get(0).getQuizHistory();
        int score = 0;
        for ( Question question : resultList ) {
            entityManager.refresh(question);
            final Variant variant = variants(question.getId()).stream().filter(Variant::isCorrect).findAny().get();
            String correctAnswer = variant.getTerm();
            final String userAnswer = question.getUserAnswer();
            System.out.println(userAnswer);
            if ( question.getQuizType().equals(QuizType.TEST) ) {
                if ( userAnswer.equals(String.valueOf(variant.getId())) ) {
                    ++score;
                    question.setCorrect(true);
                }
            } else if ( question.getQuizType().equals(QuizType.TRUE_FALSE) ) {
                if ( checkUserAnswerForTrueOrFalse(question.getId() , userAnswer) ) {
                    ++score;
                    question.setCorrect(true);
                }
            } else {
                if ( correctAnswer.equals(userAnswer) ) {
                    ++score;
                    question.setCorrect(true);
                }
            }
        }

        quizHistory.setFinishedAt(LocalDateTime.now());
        quizHistory.setCorrectAnswerCount(score);
        entityManager.getTransaction().commit();
        return quizHistory;
    }

    private boolean checkUserAnswerForTrueOrFalse(Integer questionId , String userAnswer) {

        final List<Variant> variants = variants(questionId);

        if ( ( variants.size() == 1 && userAnswer.equals("true") ) || ( variants.size() == 2 && userAnswer.equals("false") ) )
            return true;
        return false;

    }

    private List<Variant> variants(Integer questionId) {
        return entityManager.createQuery("from variant v where v.question.id=?1" , Variant.class).setParameter(1 , questionId).getResultList();
    }
}
