package main.java.web.servlet;

import main.java.domain.Cart;
import main.java.domain.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;



public class UpdateCartServlet extends HttpServlet {

    private static final String CART_FORM = "cart.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();


        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                String quantityString = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityString);


                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {
                //ignore parse exceptions on purpose
            }
        }

        req.getRequestDispatcher(CART_FORM).forward(req,resp);
    }


}
