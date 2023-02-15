package com.nooglers.dao.test;

import com.nooglers.dao.EntityProvider;
import com.nooglers.domains.User;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.enums.QuizType;
import com.nooglers.utils.ApplicationUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao implements EntityProvider {


    public List<Question> generateTest(Integer userId) {
        List<Question> questionList = new ArrayList<>();
        var quizHistoryBuilder = QuizHistory.builder();

        User createdBy = entityManager.find(User.class , userId);
        quizHistoryBuilder.createdBy(createdBy);

        entityManager.createQuery("update quiz_history qh set qh.deleted=1 where qh.finishedAt is null and qh.createdBy=?1")
                .setParameter(1 , createdBy);


        String query = """                     
                insert into public.user_progress (user_id, card_id)
                values (?, (select c.id
                            from public.card c
                            where c.id <> all (select up.card_id
                                               from public.user_progress up
                                               where up.user_id=?) limit 1));
                """;

        entityManager.createNativeQuery(query).setParameter(1 , userId).setParameter(2 , userId).executeUpdate();


        List<UserProgress> resultList = entityManager.createQuery("select up from user_progress up where up.user=?1 order by up.score " , UserProgress.class)
                .setParameter(1 , createdBy).getResultList();


        quizHistoryBuilder.startedAt(LocalDateTime.now())
                .totalQuestionCount(resultList.size());

        QuizHistory quizHistory = quizHistoryBuilder.build();
        entityManager.persist(quizHistory);


        for ( UserProgress userProgress : resultList ) {

            var questionBuilder = Question.builder()
                    .card(userProgress.getCard())
                    .quizHistory(quizHistory);

            int randomInt = ApplicationUtils.random.nextInt(3);

            if ( randomInt == 0 ) { // true-false

                String description = null;

                if ( ApplicationUtils.random.nextBoolean() ) // true
                    description = userProgress.getCard().getDescription();
                else // false
                    description = resultList.get(ApplicationUtils.random.nextInt(resultList.size())).getCard().getDescription();

                String body = userProgress.getCard().getDescription() + " matches " + description;
                boolean trueOrFalse = userProgress.getCard().getDescription().equals(description);
                Question question = questionBuilder
                        .body(body)
                        .correctAnswers(String.valueOf(trueOrFalse))
                        .wrongAnswers(String.valueOf(!trueOrFalse))
                        .quizType(QuizType.TRUE_FALSE).build();

                entityManager.persist(question);

                questionList.add(question);

            } else if ( randomInt == 1 ) {

            }

        }

        return questionList;


    }
}
