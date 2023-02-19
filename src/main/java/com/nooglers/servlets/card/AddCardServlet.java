package com.nooglers.servlets.card;

import com.nooglers.domains.Card;
import com.nooglers.domains.Document;
import com.nooglers.domains.Module;
import com.nooglers.utils.ImageUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import static com.nooglers.configs.ThreadSafeBeansContainer.*;

@WebServlet(name = "AddCardServlet", value = "/addcard")
@MultipartConfig(location = "/home/baxtigul/apps/library/upload")
public class AddCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int moduleId = (int)request.getAttribute("moduleId");
//        request.setAttribute("moduleid", moduleId);
        request.setAttribute("moduleid", 1);      /** test uchun 1 qiyati berilgan, tepadagi qator commentdan olinishi kk */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/card/add.jsp");
        requestDispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String term = request.getParameter("term");
        String description = request.getParameter("description");
        Integer moduleid = Integer.valueOf(request.getParameter("moduleid"));
        
        Part image = request.getPart("image");
        Module module = new Module();
        module.setId(moduleid);
        Card card;
        if (!image.getSubmittedFileName().equals("")){
            Document imageDocument = ImageUtils.saveImage(image);
            card = Card.builder()
                    .module(module)
                    .term(term)
                    .description(description)
                    .document(imageDocument)
                    .build();
        }else {
            card = Card.builder()
                    .module(module)
                    .term(term)
                    .description(description)
                    .build();
        }
        cardDao.get().save(card);
        response.sendRedirect("/card/test.jsp");
    }

}
