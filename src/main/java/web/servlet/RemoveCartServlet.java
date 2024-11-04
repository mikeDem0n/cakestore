package main.java.web.servlet;

import main.java.domain.Cart;
import main.java.domain.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveCartServlet extends HttpServlet {
    private static final String ERROR_FORM = "error.jsp";
    private static final String CART_FORM = "cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        String workingItemId = req.getParameter("workingItemId");


        Item item = cart.removeItemById(workingItemId);

        if (item == null) {
            session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");

           req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        } else {
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}
