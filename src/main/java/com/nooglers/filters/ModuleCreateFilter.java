package com.nooglers.filters;

import com.nooglers.utils.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "ModuleCreateFilter", urlPatterns = "/addModule")
public class ModuleCreateFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        var request = (HttpServletRequest) req;
        var response = (HttpServletResponse) res;

        String method = request.getMethod();

        if (method.equalsIgnoreCase("post")){

        }

    }
}
