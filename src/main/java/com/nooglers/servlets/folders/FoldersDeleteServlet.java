package com.nooglers.servlets.folders;

import com.nooglers.dao.FolderDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Folder;
import com.nooglers.domains.User;
import com.nooglers.enums.FolderStatus;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "FoldersDeleteServlet", value = "/FoldersDeleteServlet")
public class FoldersDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Integer userId = (Integer)session.getAttribute("user_id");
//        request.setAttribute("userId", userId);
        request.setAttribute("userId", 1);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/folder/add.jsp");
        requestDispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        System.out.println("userId = " + userId);

        FolderDao dao = new FolderDao();

        String folderId = request.getParameter("folder_id");


        dao.delete(Integer.valueOf(folderId));


        response.sendRedirect("/folder/add.jsp");
    }

}
