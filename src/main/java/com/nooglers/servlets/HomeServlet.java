package com.nooglers.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet( name = "HomeServlet", urlPatterns = "/home" )
public class HomeServlet extends HttpServlet {

}
