package ru.geekbrains.lesson2.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/firstservlet/*")
public class FirstFilter implements Filter {
    private FilterConfig config;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletResponse.getWriter().println("<h1>Filter Header</h1>");

//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        resp.sendRedirect();
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.getWriter().println("<h1>Filter Footer</h1>");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void destroy() {

    }
}
