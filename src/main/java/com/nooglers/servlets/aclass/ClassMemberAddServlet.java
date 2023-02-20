package com.nooglers.servlets.aclass;

import com.nooglers.dao.ClassDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@WebServlet(name = "ClassMemberAddServlet", value = "/group/member/add")
public class ClassMemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();
        UserDao userDao = UserDao.get();
        String user = request.getParameter("userId");
        String group = request.getParameter("groupId");
        if (!Objects.isNull(user)) {

            Integer userId = Integer.valueOf(user);
            Integer groupId = Integer.valueOf(group);
            HttpSession session = request.getSession();
            Integer sessionId = (Integer) session.getAttribute("user_id");
            Class aClass = classDao.get(groupId);
            List<Class> classList = classDao.getAll(sessionId);
            if (classList.contains(aClass)) {
                User addedUser = userDao.get(userId);
//                aClass.setUpdatedAt(LocalDateTime.now());
//                Set<User> users = aClass.getUsers();
//                users.add(addedUser);
//                aClass.setUsers(users);
                System.out.println(aClass.getUsers());
                classDao.update(aClass,addedUser);
            }
        }
        response.sendRedirect("/group");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
