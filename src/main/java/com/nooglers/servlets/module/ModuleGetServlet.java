package com.nooglers.servlets.module;

import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ModuleGetServlet", urlPatterns = "/getModule")
public class ModuleGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int moduleId = Integer.parseInt(request.getParameter("moduleId"));
//        System.out.println("moduleId in get servlet = " + moduleId);
        request.getParameter("userId");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/module/get.jsp");
        requestDispatcher.forward(request,response);
    }
}
