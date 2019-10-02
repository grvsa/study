package ru.geekbrains.lesson2.listener;

import ru.geekbrains.lesson2HW.ErrorServlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("dbConnection", null);
        try {
            HttpServlet servlet = sce.getServletContext().createServlet(ErrorServlet.class);
            servlet.init();

        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
