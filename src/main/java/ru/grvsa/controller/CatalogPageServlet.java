package ru.grvsa.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/catalog")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"admin", "adm"},
                transportGuarantee = ServletSecurity.TransportGuarantee.NONE))

public class CatalogPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/catalog.jsp").forward(req, resp);
    }
}
