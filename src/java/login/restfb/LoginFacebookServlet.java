package login.restfb;

import java.io.IOException;

import com.restfb.types.User;
import dao.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-facebook")
public class LoginFacebookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginFacebookServlet() {
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
            String accessToken = RestFB.getToken(code);
            User user = RestFB.getUserInfo(accessToken);
            entity.Account.User u = new entity.Account.User(0, user.getId(), "", user.getName(), (user.getEmail()==null)?"":user.getEmail(), "", (user.getLocale()==null)?"":user.getLocale(),"user", true);
            
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
