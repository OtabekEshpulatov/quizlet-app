package com.nooglers.servlets.card;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.CardDao;
import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Document;
import com.nooglers.domains.Module;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.dto.SendMessageDto;
import com.nooglers.services.ModuleService;
import com.nooglers.services.QuizService;
import com.nooglers.services.userprogress.UserProgressService;
import com.nooglers.utils.ImageUtils;
import com.nooglers.utils.MessageUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.nooglers.configs.ThreadSafeBeansContainer.*;

@WebServlet( name = "AddCardServlet", urlPatterns = "/addcard" )
@MultipartConfig( location = "/home/otash/apps/library/upload" )
public class AddCardServlet extends HttpServlet {


    QuizService quizService = ThreadSafeBeansContainer.QUIZ_SERVICE.get();


    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {


        Integer moduleId = Integer.valueOf(request.getParameter("mid"));
        final Integer userId = ( Integer ) request.getSession().getAttribute("user_id");

        if ( !quizService.doesUserHaveAccessToThisModule(moduleId , userId) ) {
            MessageUtil.setMessage(request , new SendMessageDto("Opps!" , "This module does not belong to you" , "study sets" , "/listModule"));
            request.getRequestDispatcher("/utils/error.jsp").forward(request , response);
        } else {
            request.setAttribute("moduleid" , moduleId);
            /** test uchun 1 qiyati berilgan, tepadagi qator commentdan olinishi kk */

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/card/add.jsp");
            requestDispatcher.forward(request , response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        ModuleDao moduleDao = ModuleDao.getInstance();

        final ModuleService moduleService = MODULE_SERVICE.get();
        final UserProgressService userProgressService = USER_PROGRESS_SERVICE.get();
        String term = request.getParameter("term");
        String description = request.getParameter("description");
        Integer moduleid = Integer.valueOf(request.getParameter("moduleid"));
        final Integer userId = ( Integer ) request.getSession().getAttribute("user_id");

//        Part image = request.getPart("image");

        Card card;
//        if ( !image.getSubmittedFileName().equals("") ) {
//            Document imageDocument = ImageUtils.saveImage(image);
//            card = Card.builder()
//                    .module(module)
//                    .title(term)
//                    .description(description)
//                    .document(imageDocument)
//                    .build();
//        } else {
        final Module moduleById = moduleDao.findById(moduleid);
        card = Card.builder().title(term)
                .module(moduleById)
                .description(description).build();
//        }

        final CardDao cardDao = CARD_DAO.get();
        cardDao.save(card);

        final Module module = moduleService.getById(moduleid);
        moduleService.updateLastSeend(module);
//        request.setAttribute("module" , module);
//
//        final List<UserProgress> up = userProgressService.getUserProgress(userId);
//        final List<UserProgress> low = up.stream().filter(userProgress -> userProgress.getScore() <= 0).toList();
//        final List<UserProgress> med = up.stream().filter(userProgress -> userProgress.getScore() > 0 && userProgress.getScore() < 15).toList();
//        final List<UserProgress> high = up.stream().filter(userProgress -> userProgress.getScore() >= 15).toList();
//
//        final List<Card> cards = moduleService.extractCards(up);
//        System.out.println(cards);
//        request.setAttribute("cards" , cards);
//        request.setAttribute("newAdded" , low);
//        request.setAttribute("inProgress" , med);
//        request.setAttribute("mastered" , high);
//
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/module/get.jsp");
//        requestDispatcher.forward(request , response);


        response.sendRedirect("/getModule?mid=" + moduleid);

    }

}
