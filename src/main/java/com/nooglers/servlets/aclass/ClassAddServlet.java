package com.nooglers.servlets.aclass;

import com.nooglers.dao.ClassDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@WebServlet(name = "ClassAddServlet", value = "/class/add")
public class ClassAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/class/add.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();

        String classname = request.getParameter("classname");
        String schoolname = request.getParameter("schoolname");
        String description = request.getParameter("description");
        String updatepermission = request.getParameter("updatepermission");
        String invitepermission = request.getParameter("invitepermission");

        HttpSession session = request.getSession();
        Object userId1 = session.getAttribute("user_id");
        Integer userId = (Integer) (userId1);
        Class aClass = Class.builder()
                .createdBy(userId)
                .name(classname)
                .schoolName(schoolname)
                .description(description)
                .permissionToInvite(!Objects.isNull(invitepermission) && invitepermission.equalsIgnoreCase("on"))
                .permissionToUpdateSets(!Objects.isNull(updatepermission) && updatepermission.equalsIgnoreCase("on"))
                .invitationLink(String.valueOf(UUID.randomUUID()))
                .build();
        classDao.save(aClass);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/class/class.jsp");
        requestDispatcher.forward(request, response);
    }
}