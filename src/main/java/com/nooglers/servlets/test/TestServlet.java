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
import java.util.Objects;

@WebServlet( name = "TestServlet", urlPatterns = "/test" )
public class TestServlet extends HttpServlet {

    QuizService quizService = ThreadSafeBeansContainer.QUIZ_SERVICE.get();

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {


        Integer userId = ( Integer ) Objects.requireNonNullElse(req.getSession().getAttribute("sessionID") , 1);
        final SolveQuestionDto solveQuestionDto = quizService.generateTest(userId);
        System.out.println(solveQuestionDto);
        req.setAttribute("question" , solveQuestionDto);
        req.setAttribute("hasNext" , true);
        req.getRequestDispatcher("/view/quiz/test.jsp").forward(req , resp);


    }


    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {


        req.getSession(false).setAttribute("remember_me" , 1);
        Integer questionId = Integer.valueOf(req.getParameter("questionId"));
        String answer = req.getParameter("value");
        Integer userId = ( Integer ) req.getSession(false).getAttribute("remember_me");
        quizService.submit(questionId , answer);

        final int questionLeft = quizService.questionLeft(userId);

        System.out.println("questions left=" + questionLeft);
        if ( questionLeft == 0 ) {
            resp.getWriter().write(quizService.finish(userId).toString());
            req.setAttribute("quizHistory" , quizService.finish(userId));
            req.getRequestDispatcher("/test/finish").forward(req , resp);
        } else {
            final SolveQuestionDto next = quizService.next(userId);
            req.setAttribute("question" , next);
            req.getRequestDispatcher("/view/quiz/test.jsp").forward(req , resp);
        }

    }
}
