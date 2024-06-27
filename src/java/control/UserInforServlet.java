
package control;

import dao.AccountDAO;
import dao.OrderDAO;
import entity.Account.User;
import entity.Order.OrderDetail;
import entity.Product.Log;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author PC
 */
@WebServlet(name="UserInforServlet", urlPatterns={"/user"})
public class UserInforServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        
        ArrayList<Log> logs = AccountDAO.getLog(user.getId());
        HashMap<Integer, List<OrderDetail>> orders = OrderDAO.getAllOrders(user.getId());
        
        request.setAttribute("orders", orders);
        request.setAttribute("logs", logs);
        
        request.getRequestDispatcher("userinfo.jsp").forward(request, response);
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
}
