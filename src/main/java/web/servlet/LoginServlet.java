// LoginServlet.java

package main.java.web.servlet;

import main.java.domain.Account;
import main.java.persistence.AccountDao;
import main.java.persistence.impl.AccountDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AccountDao accountDao;

    @Override
    public void init() throws ServletException {
        // Initialize the AccountDao implementation
        accountDao = new AccountDaoImpl() {
            @Override
            public Account getAccountByUsernameAndPassword(Account account) {
                return null;
            }

            @Override
            public void insertSignon(Account account) {

            }

            @Override
            public void updateSignon(Account account) {

            }
        };
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username and password must not be empty.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Attempt to authenticate user
        Account account = accountDao.getAccountByUsernameAndPassword(username, password);

        if (account != null) {
            // Login successful, set session attribute
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            response.sendRedirect("welcome.jsp"); // Redirect to a welcome or home page
        } else {
            // Login failed, set error message and forward to login page
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to login page on GET request
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
