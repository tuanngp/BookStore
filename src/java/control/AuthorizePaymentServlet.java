/**
 * AuthorizePaymentServlet class - requests PayPal for payment.
 * @author Nam Ha Minh
 * @copyright https://codeJava.net
 */
package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.paypal.base.rest.PayPalRESTException;
import entity.Order.CartItem;
import entity.Order.PaymentServices;
import java.util.List;

@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AuthorizePaymentServlet() {
	}

        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                List<CartItem> cart = (List<CartItem>) request.getSession().getAttribute("cart");
                if(cart==null) response.sendRedirect("error.jsp");
		
		try {
			PaymentServices paymentServices = new PaymentServices();
			String approvalLink = paymentServices.authorizePayment(cart);

			response.sendRedirect(approvalLink);
			
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
