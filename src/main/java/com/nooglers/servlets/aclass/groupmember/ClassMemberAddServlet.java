package com.nooglers.servlets.aclass.groupmember;

import com.nooglers.dao.ClassDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet( name = "ClassMemberAddServlet", value = "/group/member/add" )
public class ClassMemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        String user = request.getParameter("userId");
        String group = request.getParameter("groupId");
        if ( !Objects.isNull(user) ) {

            Integer userId = Integer.valueOf(user);
            Integer groupId = Integer.valueOf(group);
            HttpSession session = request.getSession();
            Integer sessionId = ( Integer ) session.getAttribute("user_id");
            Class aClass = classDao.get(groupId);
            List<Class> classList = classDao.getAll(sessionId);


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

            if ( classList.contains(aClass) ) {
                classDao.addMember(classDao , userDao , userId , aClass);
            }
            response.sendRedirect("/group");

        }
    }


    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws
            ServletException, IOException {


    }
}
