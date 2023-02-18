package com.nooglers.servlets.aclass;

import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@WebServlet(name = "ClassUpdateServlet", value = "/group/update")
public class ClassUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("groupId");
        if (Objects.isNull(id)) {
            response.sendRedirect("/group");
        } else {
            Integer groupId = Integer.valueOf(id);
            Class aClass = ClassDao.getInstance().get(groupId);
            request.setAttribute("group", aClass);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/group/update.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("groupId");
        if (!Objects.isNull(id)) {
            ClassDao classDao = ClassDao.getInstance();
            Integer groupId = Integer.valueOf(id);
            Class aClass = classDao.get(groupId);
            HttpSession session = request.getSession();
            Integer userId = (Integer) (session.getAttribute("user_id"));
            if (userId.equals(aClass.getCreatedBy())) {
                String classname = request.getParameter("classname");
                String schoolname = request.getParameter("schoolname");
                String description = request.getParameter("description");
                String updatepermission = request.getParameter("updatepermission");
                String invitepermission = request.getParameter("invitepermission");
                aClass.setName(classname);
                aClass.setSchoolName(schoolname);
                aClass.setDescription(description);
                aClass.setPermissionToInvite(!Objects.isNull(invitepermission) && invitepermission.equalsIgnoreCase("on"));
                aClass.setPermissionToUpdateSets(!Objects.isNull(updatepermission) && updatepermission.equalsIgnoreCase("on"));
                classDao.update(aClass);
            }
        }
        response.sendRedirect("/group");
    }
}
