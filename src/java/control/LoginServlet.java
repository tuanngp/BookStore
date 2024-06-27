package control;

import dao.AccountDAO;
import entity.Account.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        session.removeAttribute("user");
        if (AccountDAO.authenticateUser(username, password)) {
            User user = AccountDAO.searchUser(username);
            if (user.getRole().equals("admin")) {
                session.setAttribute("admin", user);
                session.setAttribute("user", user);
                response.sendRedirect("admin");
            } else if (user.getRole().equals("user")) {
                session.setAttribute("user", user);
                response.sendRedirect("home");
            }
        } else {
            request.setAttribute("msg", "username or password invalid");
            response.sendRedirect("login.jsp");
        }
    }

    private void validateInput(String username, String password, HttpServletRequest request) {
        if (username == null || username.isEmpty() || username.length() < 6) {
            setErrorStatus("Tên đăng nhập phải từ 6 kí tự trở lên.", request);
        }
        if (password == null || password.isEmpty() || password.length() < 8) {
            setErrorStatus("Mật khẩu phải từ 8 kí tự trở lên.", request);
        }
    }

    private void setErrorStatus(String message, HttpServletRequest request) {
        request.setAttribute("status", message);
    }
}
