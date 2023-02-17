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
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.nooglers.utils.ApplicationUtils.*;

public class QuestionDao implements EntityProvider {


    public SolveQuestionDto generateTest(Integer userId , Integer setId) {


        clearUnfinishedTest(userId);
        var quizHistoryBuilder = QuizHistory.builder();
        final EntityManager em = entityManager.get();

        User createdBy = em.find(User.class , userId);
        quizHistoryBuilder.createdBy(createdBy);

//        entityManager.get().createQuery("update quiz_history qh set qh.deleted=cast(1 as short ) where qh.createdBy=?1 and qh.finishedAt is null").setParameter(1 , createdBy).executeUpdate();

        String selectCards = """
                select c from Card c
                            where c <> all (select up.card
                                               from user_progress up
                                               where up.user=?1) and c.module.id=?2
                """;

        @SuppressWarnings( "unchecked" ) List<Card> cardsList = em.createQuery(selectCards , Card.class).setParameter(1 , createdBy).setParameter(2 , setId).getResultList();

        em.getTransaction().begin();


        for ( Card card : cardsList )
            em.persist(UserProgress.builder().user(createdBy).card(card).build());

        List<UserProgress> resultList = em.createQuery("select up from user_progress up where up.user=?1 and up.card.module.id=?2 order by up.score" , UserProgress.class).setParameter(1 , createdBy).setParameter(2 , setId).setMaxResults(ApplicationUtils.MAX_QUESTION_COUNT).getResultList();

        quizHistoryBuilder.startedAt(LocalDateTime.now()).totalQuestionCount(resultList.size());
        QuizHistory quizHistory = quizHistoryBuilder.build();
        em.persist(quizHistory);


        for ( UserProgress userProgress : resultList ) {

            Card card = userProgress.getCard();
            var questionBuilder = Question.builder().card(card).quizHistory(quizHistory);
            int randomInt = random.nextInt(3);
            String description = card.getDescription();
            String term = card.getTitle();

            if ( randomInt == 0 ) { // true-false

                Question question = questionBuilder.definition(description).correctAnswer(term).quizType(QuizType.TRUE_FALSE).build();
                Card randomCard = getRandomCard(card , resultList);

                if ( !random.nextBoolean() ) {
                    question.setCorrectAnswer(randomCard.getTitle());
                    final Variant variant = Variant.builder().isCorrect(false).term(randomCard.getTitle()).question(question).build();
                    em.persist(variant);
                }
                em.persist(Variant.builder().isCorrect(true).term(term).question(question).build());

            } else if ( randomInt == 1 ) {
                Question question = questionBuilder.quizType(QuizType.WRITING).correctAnswer(term).definition(description).build();
                Variant variant = Variant.builder().question(question).term(term).isCorrect(true).build();
                em.persist(variant);


            } else { // if ( randomInt == 2 )
                questionBuilder.definition(description).quizType(QuizType.TEST).build();
                Question question = questionBuilder.correctAnswer(term).build();
                List<Variant> wrongAnswers = createWrongAnswersForTest(term , question , resultList);
                wrongAnswers.add(Variant.builder().question(question).isCorrect(true).term(term).build());
                Collections.shuffle(wrongAnswers);
                addAll(wrongAnswers);
            }
        }


        em.getTransaction().commit();

        return next(userId);

    }

    private void clearUnfinishedTest(Integer userId) {

        try {
            final EntityManager em = entityManager.get();
            em.getTransaction().begin();
            em.createQuery("delete from quiz_history  q where q.createdBy.id=?1 and q.finishedAt is null ").setParameter(1 , userId).executeUpdate();
            em.getTransaction().commit();
        } catch ( RuntimeException ex ) {
            ex.printStackTrace();
        }

    }

    private void addAll(Collection<Variant> wrongAnswers) {
        for ( Variant wrongAnswer : wrongAnswers )
            entityManager.get().persist(wrongAnswer);

    }

    private Card getRandomCard(Card currentCard , List<UserProgress> resultList) {
        Collections.shuffle(resultList);
        return resultList.stream().filter(up -> up.getCard() != currentCard).findAny().get().getCard();
    }

    public SolveQuestionDto next(Integer userId) {
        try {
            final Question question = entityManager.get().createQuery("from question q  join quiz_history  qh on qh.id=q.quizHistory.id where qh.createdBy.id=?1 and q.userAnswer = null and qh.finishedAt is null" , Question.class).setParameter(1 , userId).setMaxResults(1).getSingleResult();
            return new SolveQuestionDto(question.getQuizType().name() , question.getDefinition() , variants(question.getId()) , question.getId() , question.getQuizHistory().getTotalQuestionCount() , questionLeft(userId));
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return null;
        }
    }


    private List<Variant> createWrongAnswersForTest(String correctTitle , Question question , List<UserProgress> resultList) {

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
        entityManager.get().getTransaction().begin();
        entityManager.get().createQuery("update question q set q.userAnswer=?1 where q.id=?2").setParameter(1 , answer).setParameter(2 , questionId).executeUpdate();
        entityManager.get().getTransaction().commit();
    }

    public int questionLeft(Integer userId) {
        try {
            return entityManager.get().createQuery("select count(*) from question q where q.quizHistory.createdBy.id=?1 and q.userAnswer=null and quizHistory.finishedAt is null " , Long.class).setParameter(1 , userId).getSingleResult().intValue();
        } catch ( NoResultException ex ) {
            return 0;
        }
    }


    public QuizHistory finish(Integer userId) {

        final EntityManager em = entityManager.get();
        final List<Question> resultList = em.createQuery("from question q where q.quizHistory.createdBy.id=?1 and q.quizHistory.finishedAt =null" , Question.class).setParameter(1 , userId).getResultList();
        if ( resultList.isEmpty() ) return null;

        final QuizHistory quizHistory = resultList.get(0).getQuizHistory();
        int score = 0;

        em.getTransaction().begin();

        for ( Question question : resultList ) {
            em.refresh(question);
            final Variant variant = variants(question.getId()).stream().filter(Variant::isCorrect).findAny().get();
            String correctAnswer = variant.getTerm();
            final String userAnswer = question.getUserAnswer();
            System.out.println(userAnswer);
            final Integer cardId = question.getCard().getId();

            if ( question.getUserAnswer() != null ) {
                if ( question.getQuizType().equals(QuizType.TEST) ) {
                    if ( userAnswer.equals(String.valueOf(variant.getId())) ) {
                        ++score;
                        question.setCorrect(true);
                        updateUserProgress(5 , userId , cardId);
                    } else updateUserProgress(1 , userId , cardId);

                } else if ( question.getQuizType().equals(QuizType.TRUE_FALSE) ) {
                    if ( checkUserAnswerForTrueOrFalse(question.getId() , userAnswer) ) {
                        ++score;
                        question.setCorrect(true);
                    } else updateUserProgress(1 , userId , cardId);

                } else {
                    if ( correctAnswer.equals(userAnswer) ) {
                        ++score;
                        question.setCorrect(true);
                        updateUserProgress(5 , userId , cardId);
                    } else updateUserProgress(1 , userId , cardId);

                }
            }
        }

        quizHistory.setFinishedAt(LocalDateTime.now());
        quizHistory.setCorrectAnswerCount(score);
        em.getTransaction().commit();

        return quizHistory;
    }

    private void updateUserProgress(int score , Integer userId , Integer cardId) {
        entityManager.get().createQuery("update user_progress  up set up.score=up.score+?1 where up.user.id=?2 and up.card.id=?3").setParameter(1 , score).setParameter(2 , userId).setParameter(3 , cardId).executeUpdate();
    }

    private boolean checkUserAnswerForTrueOrFalse(Integer questionId , String userAnswer) {
        final int size = variants(questionId).size();
        return ( size == 1 && userAnswer.equals("true") ) || ( size == 2 && userAnswer.equals("false") );
    }

    private List<Variant> variants(Integer questionId) {
        return entityManager.get().createQuery("from variant v where v.question.id=?1" , Variant.class).setParameter(1 , questionId).getResultList();
    }

    public List<Question> getQuestions(Integer quizHistoryId) {
        return entityManager.get().createQuery("from question  q where q.quizHistory.id=?1" , Question.class).setParameter(1 , quizHistoryId).getResultList();
    }
}
