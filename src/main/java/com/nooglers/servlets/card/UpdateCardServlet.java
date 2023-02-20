package com.nooglers.servlets.card;

import com.nooglers.dao.CardDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Document;
import com.nooglers.utils.ImageUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import static com.nooglers.configs.ThreadSafeBeansContainer.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;


@WebServlet(name = "UpdateCardServlet", value = "/editcard")
@MultipartConfig(location = "/home/otash/apps/library/uploads")
public class UpdateCardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer cardId = Integer.valueOf(request.getParameter("cardId"));
        String term = request.getParameter("term");
        String description = request.getParameter("description");

        Card card = CARD_DAO.get().get(cardId);

        Part newImage = request.getPart("image");
        if (!newImage.getSubmittedFileName().equals("")) {
            Document document = ImageUtils.saveImage(newImage);
            card.setDocument(document);
        }

        card.setTitle(term);
        card.setDescription(description);
        card.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")));
        CARD_DAO.get().update(card);

        response.sendRedirect("/getcards");
    }
}
