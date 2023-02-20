package com.nooglers.servlets.card;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.nooglers.domains.Card;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.nooglers.configs.ThreadSafeBeansContainer.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.nooglers.configs.ThreadSafeBeansContainer.CARD_DAO;

@WebServlet( name = "CardGetServlet", value = "/getcards/get/*" )
public class CardGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        Integer cardId = Integer.parseInt(request.getPathInfo().substring(1));
        Card card = CARD_DAO.get().get(cardId);


        System.out.println("cardId = " + cardId);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class , new LocalDateTimeAdapter())
                .serializeNulls()
                .setPrettyPrinting().create();

        String jsonDATA = gson.toJson(card);

        System.out.println("jsonDATA = " + jsonDATA);

        response.setContentType("application/json");
        response.getWriter().println(jsonDATA);
    }

    public static class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        @Override
        public void write(JsonWriter out , LocalDateTime value) throws IOException {
            out.value(Objects.requireNonNullElseGet(value , LocalDateTime::now).toString());
        }

        @Override
        public LocalDateTime read(JsonReader in) throws IOException {
            return LocalDateTime.parse(in.nextString());
        }
    }
}
