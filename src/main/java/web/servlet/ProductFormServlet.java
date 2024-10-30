package main.java.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.domain.Item;
import main.java.domain.Product;
import main.java.service.CatalogService;

import java.io.IOException;
import java.util.List;

public class ProductFormServlet extends HttpServlet{
    private CatalogService catalogService;
    private static final String PRODUCT_FORM = "product.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        catalogService = new CatalogService();
        Product product=catalogService.getProduct(productId);
        List<Item>  itemList = catalogService.getItemListByProduct(productId);
        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);
        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
    }
}
