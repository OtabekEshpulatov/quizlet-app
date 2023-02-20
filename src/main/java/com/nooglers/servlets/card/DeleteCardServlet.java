package com.nooglers.servlets.card;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import static com.nooglers.configs.ThreadSafeBeansContainer.*;
@WebServlet(name = "DeleteCardServlet", value = "/deletecard")
public class DeleteCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardId = request.getParameter("cardId");
        System.out.println("cardId = " + cardId);

        CARD_DAO.get().deleteById(Integer.valueOf(cardId));
        response.sendRedirect("/getcards");
    }
}