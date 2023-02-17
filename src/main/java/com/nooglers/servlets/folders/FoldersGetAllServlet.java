package com.nooglers.servlets.folders;

import com.nooglers.dao.FolderDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Folder;
import com.nooglers.domains.User;
import com.nooglers.enums.FolderStatus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FolderAddServlet", urlPatterns = "/folder/getall")
public class FoldersGetAllServlet extends HttpServlet {
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

        User user = new User();
        user.setId(Integer.valueOf(userId));
        final FolderDao dao = new FolderDao();
        final UserDao userDao = new UserDao();
        String title = request.getParameter("title");
        String description = request.getParameter("description");


        Folder folder = Folder.builder()
                .title(title)
                .description(description)
                .status(FolderStatus.CREATED)
                .createdBy(user)
                .build();

        dao.get(folder.getId());

        response.sendRedirect("/folder/add.jsp");
    }

}
