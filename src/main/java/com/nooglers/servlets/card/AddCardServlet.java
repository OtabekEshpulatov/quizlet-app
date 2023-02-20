package com.nooglers.servlets.card;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.CardDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Document;
import com.nooglers.domains.Module;
import com.nooglers.utils.ImageUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static com.nooglers.configs.ThreadSafeBeansContainer.*;

@WebServlet( name = "AddCardServlet", value = "/addcard" )
@MultipartConfig( location = "/home/otash/apps/library/uploads" )
public class AddCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
//        int moduleId = (int)request.getAttribute("moduleId");
//        request.setAttribute("moduleid", moduleId);
        request.setAttribute("moduleid" , 1);      /** test uchun 1 qiyati berilgan, tepadagi qator commentdan olinishi kk */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/card/add.jsp");
        requestDispatcher.forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {


        String term = request.getParameter("term");
        String description = request.getParameter("description");
        Integer moduleid = Integer.valueOf(request.getParameter("moduleid"));

        Part image = request.getPart("image");
        Module module = new Module();
        module.setId(moduleid);
        Card card;
        if ( !image.getSubmittedFileName().equals("") ) {
            Document imageDocument = ImageUtils.saveImage(image);
            card = Card.builder()
                    .module(module)
                    .title(term)
                    .description(description)
                    .document(imageDocument)
                    .build();
        } else {
            card = Card.builder()
                    .module(module)
                    .title(term)
                    .description(description)
                    .build();
        }
        CARD_DAO.get().save(card);
        response.sendRedirect("/view/card/test.jsp");
    }

}
