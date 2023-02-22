package com.nooglers.servlets.aclass.groupmember;

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

@WebServlet(name = "ClassMemberAddServlet", value = "/group/member/add")
public class ClassMemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        Class aClass = classDao.get(groupId);
        User user = userDao.findById(userId);
        aClass.getUsers().add(user);
        aClass.setUpdatedAt(LocalDateTime.now());
        classDao.update(aClass);
        //public String  addSetToClass(Integer classId, Integer moduleId){
        //
        //     entityManager.getTransaction().begin();
        //        Class aClass = entityManager.find(Class.class, classId);
        //        Module module = entityManager.find(Module.class, moduleId);
        //        if(aClass==null){
        //            return "Class not found";
        //        }
        //        if(module==null){
        //            return "Module not found";
        //        }
        //
        //        module.getModuleClass().add(aClass);
        //        entityManager.persist(aClass);
        //        entityManager.persist(module);
        //
        //        entityManager.getTransaction().commit();
        //
        //        return "Ok";
        //    }

//            if ( classList.contains(aClass) ) {
//                classDao.addMember(classDao , userDao , userId , aClass);
//            }
        response.sendRedirect("/group");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


    }
}
