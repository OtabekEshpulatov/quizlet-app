package com.nooglers.servlets.aclass;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.domains.Class;
import com.nooglers.services.ClassService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet( name = "MyClassServlet", urlPatterns = "/mygroup" )
public class MyClassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {


        final ClassService classService = ThreadSafeBeansContainer.CLASS_SERVICE.get();
        final Integer groupId = Integer.valueOf(req.getParameter("gid"));
        final Class group = classService.getGroup(groupId);
        req.setAttribute("group" , group);
        req.getRequestDispatcher("/view/group/mygroup.jsp").forward(req , resp);


    }
}
