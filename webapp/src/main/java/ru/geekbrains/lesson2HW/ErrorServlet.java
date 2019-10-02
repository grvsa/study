package ru.geekbrains.lesson2HW;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int code = (int) req.getAttribute("javax.servlet.error.status_code");
        String urlString = req.getRequestURL().toString();

        if (req.getPathInfo() != null){
            urlString = urlString.replace(req.getPathInfo().toString(),"");
        }
        urlString = urlString.replace(req.getServletPath().toString(),"");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        switch (code) {
            case 404:
                writer.println("<h1>Запрошеная страница не найдена.</h1>");
                break;
            case 403:
                writer.println("<h1>Доступ к странице ограничен.</h1>");
                break;
            default:
                writer.println("<h1>Произошла ошибка.</h1>");
                break;
        }
        writer.println("<a href=\"" + urlString + "/main\">Главная</a>");
    }
}
