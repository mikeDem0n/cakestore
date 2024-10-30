package main.java.web.servlet;

import main.java.domain.Item;
import main.java.domain.Product;
import main.java.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ItemFormServlet extends HttpServlet {
    private CatalogService catalogService;
    private static final String ITEM_FORM = "item.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        catalogService = new CatalogService();
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();
        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("item", item);
        req.getRequestDispatcher(ITEM_FORM).forward(req, resp);
    }
}
