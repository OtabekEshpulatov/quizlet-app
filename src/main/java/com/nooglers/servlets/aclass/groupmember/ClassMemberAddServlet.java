package com.nooglers.servlets.aclass.groupmember;

import com.nooglers.dao.ClassDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ClassMemberAddServlet", value = "/group/member/add")
public class ClassMemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        Class aClass = classDao.get(groupId);
        User user = userDao.findById(userId);
        aClass.getUsers().add(user);
        aClass.setUpdatedAt(LocalDateTime.now());
        classDao.update(aClass);
        response.sendRedirect("/group");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


    }
}
