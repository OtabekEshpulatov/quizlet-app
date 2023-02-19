package com.nooglers.servlets.auth;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.User;
import com.nooglers.utils.AES;
import com.nooglers.utils.Encrypt;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next = request.getParameter("next");

        request.setAttribute("next", next);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String loginemail = request.getParameter("loginemail");
            String loginpassword = request.getParameter("loginpassword");
            UserDao userDao = UserDao.getInstance();
            User user = userDao.get(loginemail);
            if (user == null || !Encrypt.checkPassword(loginpassword, user.getPassword())) {
                throw new RuntimeException("Bad credentials");
            }
            Integer userId = user.getId();
            String encrypt = AES.encrypt(userId.toString());
            ThreadSafeBeansContainer.COOKIE_VALUES.put(userId, encrypt);

            Cookie cookie = new Cookie("remember_me", encrypt);
            cookie.setMaxAge(5 * 60 * 60);
            response.addCookie(cookie);
            String next = request.getParameter("next");
            if (Objects.isNull(next)||next.isBlank()) {
                response.sendRedirect("/home");
            } else {
                response.sendRedirect(next);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            request.setAttribute("login_credentials", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
