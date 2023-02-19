package com.nooglers.servlets.auth;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.User;
import com.nooglers.utils.AES;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String signupemail = request.getParameter("signupemail");
            String signupusername = request.getParameter("signupusername");
            String signuppassword = request.getParameter("signuppassword");
            User user = User.builder()
                    .email(signupemail)
                    .username(signupusername)
                    .password(signuppassword)
                    .build();
            UserDao userDao = UserDao.getInstance();
            user = userDao.save(user);
            Integer userId = user.getId();
            String encrypt = AES.encrypt(userId.toString());
            ThreadSafeBeansContainer.COOKIE_VALUES.put(userId, encrypt);
            Cookie cookie = new Cookie("remember_me", encrypt);
            cookie.setMaxAge(5 * 60 * 60);
            response.addCookie(cookie);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } catch (RuntimeException e) {
            request.setAttribute("credentials", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/signup.jsp");
            dispatcher.forward(request, response);
        }
    }
}
