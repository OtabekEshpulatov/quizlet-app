package com.nooglers.servlets.test;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.services.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

@WebServlet( name = "TestServlet", urlPatterns = "/test" )
public class TestServlet extends HttpServlet {

    QuizService quizService = ThreadSafeBeansContainer.QUIZ_SERVICE.get();

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = ( Integer ) req.getSession().getAttribute("user_id");
        Integer moduleId = Integer.valueOf(req.getParameter("m_id") );

        if ( !quizService.doesUserHaveAccessToThisModule(moduleId , userId) ) {
            String m1 = "Opps!";
            String m2 = "You cannot take a test. This module does not belong to you.";
            req.setAttribute("message1" , m1);
            req.setAttribute("message2" , m2);
            req.setAttribute("message3" , "modules");
            req.setAttribute("url" , "/getModule?m_id=" + moduleId);
            req.getRequestDispatcher("/utils/error.jsp").forward(req , resp);
        } else if ( quizService.numberOfQuestions(moduleId) < 2 ) {
            req.setAttribute("message1" , "Opps!");
            req.setAttribute("message2" , "You don't have enough cards to start quizzes.");
            req.setAttribute("message3" , " cards");
            req.setAttribute("url" , "Go to cards");
            req.getRequestDispatcher("/utils/error.jsp").forward(req , resp);

        } else {
            final SolveQuestionDto solveQuestionDto = quizService.generateTest(userId , moduleId);
            System.out.println(solveQuestionDto);
            req.setAttribute("question" , solveQuestionDto);
            req.setAttribute("hasNext" , true);
            req.getRequestDispatcher("/view/quiz/test.jsp").forward(req , resp);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {


        Integer questionId = Integer.valueOf(req.getParameter("questionId"));
        String answer = req.getParameter("value");
        Integer userId = ( Integer ) req.getSession().getAttribute("user_id");
        quizService.submit(questionId , answer);

        final int questionLeft = quizService.questionLeft(userId);

        System.out.println("questions left=" + questionLeft);
        if ( questionLeft == 0 ) {
//            req.setAttribute("result" , quizService.finish(userId));
            resp.sendRedirect("/test/result");
        } else {
            final SolveQuestionDto next = quizService.next(userId);
            req.setAttribute("question" , next);
            req.getRequestDispatcher("/view/quiz/test.jsp").forward(req , resp);
        }


//        Duration.ofHours(ChronoUnit.HOURS.between(LocalDateTime.now() , LocalDateTime.now().plusHours(10))).toHours()
    }
}
