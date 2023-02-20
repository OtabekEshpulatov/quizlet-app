package com.nooglers.servlets.aclass;

import com.nooglers.dao.UserDao;
import com.nooglers.domains.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ClassMemberServlet", value = "/group/member")
public class ClassMemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.get();
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        request.setAttribute("groupId", groupId);
        request.setAttribute("users", userDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/group/member/member.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        request.setAttribute("groupId", groupId);
        UserDao userDao = UserDao.get();
        if (!Objects.isNull(username)) {
            List<User> users = userDao.getAll(username);
            request.setAttribute("users", users);
        } else {
            request.setAttribute("users", userDao.getAll());
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/group/member/member.jsp");
        requestDispatcher.forward(request, response);
    }
}
