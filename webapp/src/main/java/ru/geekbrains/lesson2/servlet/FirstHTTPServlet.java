package ru.geekbrains.lesson2.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/first/http/servlet")
public class FirstHTTPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        resp.getWriter().println("<h1>FirstHTTPServlet</h1>");
        resp.getWriter().println("<h2>URL parameters</h2>");
        resp.getWriter().println("<h3>" + req.getScheme() + "</h3>");
        resp.getWriter().println("<h3>" + req.getContextPath()+ "</h3>");
        resp.getWriter().println("<h3>" + req.getServletPath()+ "</h3>");
        resp.getWriter().println("<h3>" + req.getRequestURL().toString()+ "</h3>");
        resp.getWriter().println("<h3>" + req.getQueryString()+ "</h3>");
        resp.getWriter().println("<h3>" + req.getPathInfo()+ "</h3>");

        for (Map.Entry<String, String[]> map:
             req.getParameterMap().entrySet()) {
            resp.getWriter().printf("<p>%s=%s</p>", map.getKey(), String.join(" ", map.getValue()));
        }

//        resp.sendRedirect();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
