package com.nooglers.servlets.quizservlets;

import com.nooglers.dao.QuizHistoryDao;
import com.nooglers.domains.test.QuizHistory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;




@WebServlet(name = "QuizHistoryByUserIdServlet", urlPatterns = "/historyUser/*")
public class QuizHistoryByUserIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/quiz/quizByHistoryByUserId");
        String pathInfo = req.getPathInfo();
        int userId= Integer.parseInt(pathInfo.substring(1));
        QuizHistoryDao quizHistoryDao=new QuizHistoryDao();
        List<QuizHistory> recentTestResults = quizHistoryDao.getRecentTestResults(userId);
        req.setAttribute("quizhistorybyUserId",recentTestResults);
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}

