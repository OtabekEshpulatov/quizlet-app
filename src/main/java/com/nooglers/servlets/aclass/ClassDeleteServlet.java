package com.nooglers.servlets.aclass;

import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@WebServlet(name = "ClassDeleteServlet", value = "/group/delete")
public class ClassDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("groupId");
        if (Objects.isNull(id)) {
            response.sendRedirect("/group");
        } else {
            Integer groupId = Integer.valueOf(id);
            Class aClass = ClassDao.getInstance().get(groupId);
            request.setAttribute("group", aClass);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/group/delete.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        ClassDao classDao = ClassDao.getInstance();
        Class aClass = classDao.get(groupId);
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId.equals(aClass.getCreatedBy())) {
            classDao.delete(groupId);
        }
        response.sendRedirect("/group");
    }
}
