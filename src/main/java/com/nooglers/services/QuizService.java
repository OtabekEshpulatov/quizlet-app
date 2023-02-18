package com.nooglers.services;

import com.nooglers.dao.test.QuestionDao;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.domains.test.Variant;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.enums.QuizType;
import lombok.NonNull;

import java.util.List;

import static com.nooglers.configs.ThreadSafeBeansContainer.QUESTION_DAO;


public class QuizService {

    public static final QuestionDao dao = QUESTION_DAO.get();

    public SolveQuestionDto generateTest(@NonNull Integer userId , Integer setId) {
        return dao.generateTest(userId , setId);
    }

    public SolveQuestionDto next(@NonNull Integer userId) {
        return dao.next(userId);
    }

    public void submit(@NonNull Integer questionId , String answer) {
        dao.submit(questionId , answer);
    }

    public int questionLeft(Integer userId) {
        return dao.questionLeft(userId);
    }

    public QuizHistory finish(Integer userId) {
        return dao.finish(userId);
    }

    public List<Question> getQuestions(Integer quizHistoryId) {
        return dao.getQuestions(quizHistoryId);
    }

    public String getTerm(Question question) {
        return dao.getTerm(question.getId());
    }

    public String getUserAnswer(Question question) {
        if ( question.getUserAnswer() == null ) return "did not answer"; // TODO localized message
        if ( question.getQuizType().equals(QuizType.TEST) )
            return getVariantById(Integer.valueOf(question.getUserAnswer()) , question).getTerm();
        return question.getUserAnswer();

    }

    private Variant getVariantById(Integer variantId , Question question) {
        return dao.getVariantById(variantId);
    }



    private String getTerm(List<Variant> variants) {
        return variants.stream().filter(Variant::isCorrect).map(Variant::getTerm).findAny().get();
    }
}
