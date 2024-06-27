
package control;

import dao.AccountDAO;
import entity.Account.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(name="UserServlet", urlPatterns={"/userAdmin"})
public class UserAdminServlet extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String msg = "";
        String type = request.getParameter("type");
        try {
            String action = request.getParameter("action");
            
            User user = getUserFromRequest(request);
            
            switch (action) {
                case "update":
                    msg = AccountDAO.updateUser(user) ? "Cập nhật thông tin người dùng thành công!" : "Đã xảy ra lỗi, cập nhật thông tin người dùng thất bại!";
                    break;
                case "delete":
                    msg = AccountDAO.deleteUser(user) ? "Xóa ID: "+ user.getId() + " thành công!" : "Xóa thất bại!";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid action: " + action);
            }
        } catch (IllegalArgumentException e) {
        } finally {
            HttpSession session = request.getSession();
            User user = getUserFromRequest(request);
            if (type != null && type.equals("user")) {
                session.setAttribute("user", AccountDAO.searchUser(user.getUsername()));
                session.setAttribute("status", msg);
                response.sendRedirect("user");
            } else {
                session.setAttribute("statusAdmin", msg);
                response.sendRedirect("admin#users");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private User getUserFromRequest(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("userId"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String role = request.getParameter("role");
            boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));
            return new User(id, username, password, name, email, phone, address, role, isActive);
        } catch (NumberFormatException e) {
        }
        return null;
    }
}
