package com.nooglers.servlets.aclass;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import com.nooglers.services.ClassService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyClassServlet", urlPatterns = "/mygroup")
public class MyClassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ClassService classService = ThreadSafeBeansContainer.CLASS_SERVICE.get();
        final Integer groupId = Integer.valueOf(req.getParameter("gid"));
        final Integer userId = (Integer) req.getSession().getAttribute("user_id");
        final Class group = classService.getGroup(groupId);
        req.setAttribute("isOwner", group.getCreatedBy().equals(userId));
        req.setAttribute("group", group);
        List<Module> moduleList = group.getModuleList();
        req.setAttribute("modules", moduleList);

        for (Module module : moduleList) {
            System.out.println(module);
        }
//        dao.getAllModules(group);

        req.getRequestDispatcher("/view/group/mygroup.jsp").forward(req, resp);
    }
}
