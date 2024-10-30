package main.java.web.servlet;

import main.java.domain.Category;
import main.java.domain.Product;
import main.java.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CategoryFormServlet extends HttpServlet {
    private CatalogService catalogService;

    private static final String CATEGORY_FORM = "/WEB-INF/jsp/main.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String categoryId = req.getParameter("categoryId");
      catalogService = new CatalogService();
        Category category = catalogService.getCategory(categoryId);
      List<Product> productList =  catalogService.getProductListByCategory(categoryId);
      HttpSession session = req.getSession();
      session.setAttribute("category", category);
      session.setAttribute("productList", productList);
      req.getRequestDispatcher(CATEGORY_FORM).forward(req, resp);

    }
}
