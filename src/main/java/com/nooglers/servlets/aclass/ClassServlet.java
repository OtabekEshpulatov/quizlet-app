package com.nooglers.servlets.aclass;

import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ClassServlet", value = "/group")
public class ClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        ClassDao classDao = ClassDao.getInstance();
        List<Class> classes = classDao.getAll(userId);
        request.setAttribute("classes", classes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/group/group.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
