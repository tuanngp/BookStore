/**
 * ReviewPaymentServlet class - show review payment page.
 * @author Nam Ha Minh
 * @copyright https://codeJava.net
 */
package control;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import entity.Order.PaymentServices;

@WebServlet("/review_payment")
public class ReviewPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReviewPaymentServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		try {
			PaymentServices paymentServices = new PaymentServices();
			Payment payment = paymentServices.getPaymentDetails(paymentId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			request.setAttribute("payer", payerInfo);
			request.setAttribute("transaction", transaction);
			request.setAttribute("shippingAddress", shippingAddress);
			
			String url = "review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
			
			request.getRequestDispatcher(url).forward(request, response);
			
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}		
	}

}
