package com.nooglers.servlets.test;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.domains.test.Question;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.services.QuizService;
import com.nooglers.utils.ApplicationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet( name = "QuizServlet", urlPatterns = "/test" )
public class QuizServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        QuizService quizService = ThreadSafeBeansContainer.QUIZ_SERVICE.get();
        Integer userId = ( Integer ) Objects.requireNonNullElse(req.getSession().getAttribute("sessionID") , 1);
        final SolveQuestionDto solveQuestionDto = quizService.generateTest(userId);
        System.out.println(solveQuestionDto);
        req.setAttribute("question" , solveQuestionDto);
        req.getRequestDispatcher("/view/quiz/test.jsp").forward(req , resp);



    }


    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {


    }
}
