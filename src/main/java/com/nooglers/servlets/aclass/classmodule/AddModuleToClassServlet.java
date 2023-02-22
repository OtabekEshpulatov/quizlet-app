package com.nooglers.servlets.aclass.classmodule;

import com.nooglers.domains.Module;
import com.nooglers.services.ClassService;
import com.nooglers.utils.Validators;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "AddModuleToClassServlet", value = "/class/module/add")
public class AddModuleToClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id = request.getParameter("groupId");
//        if (Validators.checkForNullOrBlank(id)) {
//            ClassService classService = ClassService.getInstance();
//            HttpSession session = request.getSession();
//            Integer userId = (Integer) session.getAttribute("user_id");
//            Integer groupId = Integer.valueOf(id);
//            Set<Module> groupModules = classService.getGroupModules(groupId, userId);
//            request.setAttribute("modules", groupModules);
//            request.setAttribute("groupId", groupId);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/group/module/classmoduleadd.jsp");
//            requestDispatcher.forward(request, response);
//        }
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        Integer moduleId = Integer.valueOf(request.getParameter("moduleId"));
        ClassService classService = ClassService.getInstance();
        classService.addClassModule(groupId, moduleId);
        response.sendRedirect("/class/module/get?groupId=" + groupId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        Integer moduleId = Integer.valueOf(request.getParameter("moduleId"));
        ClassService classService = ClassService.getInstance();
        classService.addClassModule(groupId, moduleId);
        response.sendRedirect("/class/module/get?groupId=" + groupId);
    }
}
