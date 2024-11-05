package main.java.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AccountFormServlet extends HttpServlet {
    private static final String ACCOUNT_FORM = "/WEB-INF/jsp/account/includeAccount.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ACCOUNT_FORM).forward(req,resp);
    }
}
