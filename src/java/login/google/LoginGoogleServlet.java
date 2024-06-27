package login.google;

import dao.AccountDAO;
import entity.Account.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @author tuanngp
 */
@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            User u = new User(0, googlePojo.getId(), "", googlePojo.getName(), googlePojo.getEmail(), "", "", "user", true);
            if (AccountDAO.searchUser(u.getUsername()) != null) {
                request.getSession().setAttribute("user", AccountDAO.searchUser(u.getUsername()));
                response.sendRedirect("home");

            } else {
                AccountDAO.registerUser(u);
                request.getSession().setAttribute("user", AccountDAO.searchUser(u.getUsername()));
                response.sendRedirect("user");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
