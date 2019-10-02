package ru.geekbrains.lesson2.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;

public class FirstServlet implements Servlet, Serializable {
    private transient ServletConfig config;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().println("<h1>Hello from First Servlet</h1>");
        Boolean redirected = (Boolean) servletRequest.getAttribute("forwarded");
        if (redirected != null && redirected == true){
            servletResponse.getWriter().println("<h1>Redirected</h1>");
        }
    }

    @Override
    public String getServletInfo() {
        return "First Servlet";
    }

    @Override
    public void destroy() {

    }
}
