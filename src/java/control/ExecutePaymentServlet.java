/**
 * ExecutePaymentServlet class - execute payment via PayPal.
 *
 * @author Nam Ha Minh
 * @copyright https://codeJava.net
 */
package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import dao.OrderDAO;
import entity.Account.User;
import entity.Order.CartItem;
import entity.Order.PaymentServices;
import java.util.List;

@WebServlet("/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ExecutePaymentServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        try {
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.executePayment(paymentId, payerId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            HttpSession session = request.getSession();
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            User user = (User) session.getAttribute("user");
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            // Thêm thông tin đơn hàng vào database ở đây
            OrderDAO.addOrder(user, cart);
            Utils.Email.sendThankYouEmail(user.getEmail(), user.getName(), cart);
            // Chuyển hướng người dùng về trang chủ sau khi thanh toán
            session.removeAttribute("cart");
            request.getRequestDispatcher("receipt.jsp").forward(request, response);

        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
