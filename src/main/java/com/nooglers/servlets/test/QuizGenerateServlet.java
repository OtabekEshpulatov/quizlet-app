package com.nooglers.servlets.test;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.domains.test.Question;
import com.nooglers.services.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "QuizGenerateServlet", urlPatterns = "/test" )
public class QuizGenerateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        QuizService quizService = ThreadSafeBeansContainer.QUIZ_SERVICE.get();
        Integer userId = ( Integer ) req.getSession().getAttribute("sessionID");

        List<Question> questionList = quizService.generateTest(userId);


    }
}
