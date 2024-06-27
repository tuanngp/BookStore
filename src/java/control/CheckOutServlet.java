// This servlet is used to handle the checkout process. It will get the cart from the session and display the cart items to the user.
package control;

import dao.OrderDAO;
import entity.Account.User;
import java.io.IOException;
import entity.Order.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/checkout"})
public class CheckOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("thanhtoan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin giỏ hàng từ session
        HttpSession session = request.getSession();
        String method = request.getParameter("method");
        User user = (User) session.getAttribute("user");
        List<CartItem> cart = (List<CartItem>) request.getSession().getAttribute("cart");
        
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
        } else {
            if (method != null && !method.isEmpty()) {
                if (method.equals("cod")) {
                    double totalAmount = cart.stream()
                            .mapToDouble(t -> t.getBook().getPrice() * t.getQuantity())
                            .reduce(0, (a, b) -> a + b);
                    Utils.Email.sendThankYouEmail(user.getEmail(), user.getName(), cart);
                    double shippingFee = 30000;
                    totalAmount += shippingFee;
                    OrderDAO.addOrder(user, cart);
                    request.setAttribute("cart", cart);
                    session.removeAttribute("cart");
                    request.setAttribute("totalAmount", totalAmount);
                    request.setAttribute("shipCode", shippingFee);
                    // Thêm thông tin đơn hàng vào database ở đây
                    OrderDAO.addOrder(user, cart);
                    // Chuyển hướng người dùng về trang chủ sau khi thanh toán
                    request.getRequestDispatcher("success.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("authorize_payment").forward(request, response);
                }
            }
        }
    }
}
