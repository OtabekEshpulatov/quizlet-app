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

@WebServlet(name = "QuizHistoryServlet", value= "/history")
public class QuizHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/quiz/quizHistory.jsp");
        QuizHistoryDao quizHistoryDao=new QuizHistoryDao();
        List<QuizHistory> historyList = quizHistoryDao.getAll();
        req.setAttribute("historyAll",historyList);
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
