package com.nooglers.servlets.module;

import com.nooglers.dao.ModuleDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ModuleDeleteServlet", value = "/deleteModule")
public class ModuleDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/module/delete.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("moduleId"));
        System.out.println(" module deleted id = " + id);
        ModuleDao dao = ModuleDao.getInstance();
        dao.delete(id);

        response.sendRedirect("/home");
    }
}
