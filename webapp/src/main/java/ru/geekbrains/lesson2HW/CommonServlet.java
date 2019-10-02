package ru.geekbrains.lesson2HW;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommonServlet extends HttpServlet {
    protected String name;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String urlString = req.getRequestURL().toString();

        if (req.getPathInfo() != null){
            urlString = urlString.replace(req.getPathInfo().toString(),"");
        }
        urlString = urlString.replace(req.getServletPath().toString(),"");


        writer.println("<html><head><title>");
        writer.println(name);
        writer.println("</title></head><body>");
        writer.println("<h1>" + name + "</h1>");
        writer.println("<hr>");
        writer.println("<ul>");
        writer.println("<li><a href=\"" + urlString + "/main\">Главная</a></li>");
        writer.println("<li><a href=\"" + urlString + "/catalog\">Каталог</a></li>");
        writer.println("<li><a href=\"" + urlString + "/product\">Товар</a></li>");
        writer.println("<li><a href=\"" + urlString + "/cart\">Корзина</a></li>");
        writer.println("<li><a href=\"" + urlString + "/order\">Заказ</a></li>");
        writer.println("</ul>");
    }
}
