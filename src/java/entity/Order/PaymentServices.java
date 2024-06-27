
package entity.Order;

import java.util.ArrayList;
import java.util.List;
import entity.Order.CartItem;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentServices {
	private static final String CLIENT_ID = "AdHyh5EmQfazo6tZcvqtgbVCx7CgSV_GrFPUWhMAgsq-JmJypEy1PW94UI0xwydMPq0OzBOFFXBbO2iF";
	private static final String CLIENT_SECRET = "EEfR90qJWYAr4OJZsR16ITv4y5W6hD1UqjSK3fwi_Rvgr-MVSiHejLrW6TnfuIii32KKTDvNgKxZw_W4";
	private static final String MODE = "sandbox";

	public String authorizePayment(List<CartItem> cart)			
			throws PayPalRESTException {		

		Payer payer = getPayerInformation();
		RedirectUrls redirectUrls = getRedirectURLs();
		List<Transaction> listTransaction = getTransactionInformation(cart);
		
		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransaction);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setPayer(payer);
		requestPayment.setIntent("authorize");

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		Payment approvedPayment = requestPayment.create(apiContext);

		System.out.println("=== CREATED PAYMENT: ====");
		System.out.println(approvedPayment);

		return getApprovalLink(approvedPayment);

	}
	
	private Payer getPayerInformation() {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName("William")
				 .setLastName("Peterson")
				 .setEmail("william.peterson@company.com");
		
		payer.setPayerInfo(payerInfo);
		
		return payer;
	}
	
	private RedirectUrls getRedirectURLs() {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:9999/BookStore/cancel.jsp");
		redirectUrls.setReturnUrl("http://localhost:9999/BookStore/review_payment");
		
		return redirectUrls;
	}
	
	private List<Transaction> getTransactionInformation(List<CartItem> cart) {
		Details details = new Details();
		details.setShipping("2");
                Integer total=0;
                for(CartItem c: cart){
                    total +=((int)(c.getBook().getPrice()/22000))*c.getQuantity();
                }
                
		details.setSubtotal(total.toString());
		details.setTax("0");
                total+=2;
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal( total.toString());
		amount.setDetails(details);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription("Thanh toán cho BookStore");
		
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();
		for(CartItem c: cart){
		Item item = new Item();
		item.setCurrency("USD");
		item.setName(c.getBook().getTitle());
                Integer price =(int)c.getBook().getPrice()/22000;
		item.setPrice( price.toString());
		item.setTax("0");
		item.setQuantity(String.valueOf(c.getQuantity()));
		
		items.add(item);
		itemList.setItems(items);
                }
		transaction.setItemList(itemList);

		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction);	
		
		return listTransaction;
	}
	
	private String getApprovalLink(Payment approvedPayment) {
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;
		
		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
				break;
			}
		}		
		
		return approvalLink;
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);

		Payment payment = new Payment().setId(paymentId);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		return payment.execute(apiContext, paymentExecution);
	}
	
	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, paymentId);
	}
}
