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


@WebServlet(name = "QuizHistoryIdServlet", urlPatterns = "/histroy/*")
public class QuizHistoryIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/quiz/quizHByHistoryId");
        String pathInfo = req.getPathInfo();
        int historyId = Integer.parseInt(pathInfo.substring(1));
        QuizHistoryDao quizHistoryDao=new QuizHistoryDao();
        QuizHistory quizHistory = quizHistoryDao.get(historyId);
        req.setAttribute("quizhistory",quizHistory);
        dispatcher.forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
