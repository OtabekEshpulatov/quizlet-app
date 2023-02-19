package com.nooglers.servlets.module;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.services.ModuleService;
import com.nooglers.services.QuizService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet( name = "ModuleGetServlet", urlPatterns = "/getModule" )
public class ModuleGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        final QuizService quizService = ThreadSafeBeansContainer.QUIZ_SERVICE.get();
        final ModuleService moduleService = ThreadSafeBeansContainer.MODULE_SERVICE.get();
        final Integer moduleId = Integer.valueOf(request.getParameter("m_id"));
        final Integer userId = ( Integer ) Objects.requireNonNullElse(request.getSession().getAttribute("user_id") , 1);
        System.out.println(userId);


        if ( !quizService.doesUserHaveAccessToThisModule(moduleId , userId) ) {
            String m1 = "Opps! You did something Wrong";
            String m2 = "This module does not belong to you";
            request.setAttribute("message1" , m1);
            request.setAttribute("message2" , m2);
            request.getRequestDispatcher("/utils/error.jsp").forward(request , response);
        } else {
            request.setAttribute("module" , moduleService.getById(moduleId));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/module/get.jsp");
            requestDispatcher.forward(request , response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
//        int moduleId = Integer.parseInt(request.getParameter("moduleId"));
//        System.out.println("moduleId in get servlet = " + moduleId);
//
//        request.getParameter("userId");
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/module/get.jsp");
//        requestDispatcher.forward(request , response);

        response.sendError(405 , "Method not allowed");
    }
}
