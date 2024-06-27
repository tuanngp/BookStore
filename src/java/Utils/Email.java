package Utils;

import entity.Order.CartItem;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

    public static void sendThankYouEmail(String to, String userName, List<CartItem> cart) {
        final String from = "lekhanhduccc@gmail.com";
        final String password = "xzrqwgggfaybngsk";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Cảm ơn bạn đã mua hàng");
            msg.setSentDate(new Date());

            // Tạo nội dung email
            MimeMultipart multipart = createHtmlContent(cart, userName);
            msg.setContent(multipart);

            // Gửi email
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static MimeMultipart createHtmlContent(List<CartItem> cart, String userName) throws MessagingException {
        MimeMultipart multipart = new MimeMultipart();

        // Tạo phần nội dung của email
        MimeBodyPart greetingPart = new MimeBodyPart();
        greetingPart.setText("Chân thành cảm ơn " + userName + " đã chọn [BookStore] của chúng tôi. Dưới đây là chi tiết đơn hàng bạn vừa mua:");

        MimeBodyPart contentPart = new MimeBodyPart();
        StringBuilder emailContent = new StringBuilder("<table border='1'>");
        emailContent.append("<tr>"
                + "<th>Sản phẩm</th>"
                + "<th>Số lượng</th>"
                + "<th>Giá</th>"
                + "<th>Tạm tính</th>"
                + "</tr>");

        for (CartItem item : cart) {
            emailContent.append("<tr>")
                    .append("<td>").append(item.getBook().getTitle()).append("</td>")
                    .append("<td>").append(item.getQuantity()).append("</td>")
                    .append("<td>").append(item.getBook().getPrice()).append("</td>")
                    .append("<td>").append(item.getBook().getPrice() * item.getQuantity()).append("</td>")
                    .append("</tr>");
        }

        emailContent.append("</table>");

        emailContent.append("<p><strong>Tổng giá trị đơn hàng:</strong> ").append(calculateTotalAmount(cart)).append("</p>")
                .append("<p><strong>Phí vận chuyển:</strong> 30000 VND</p>")
                .append("<p><strong>Tổng cộng:</strong> ").append(calculateTotalAmount(cart) + 30000).append("</p>");

        contentPart.setContent(emailContent.toString(), "text/html; charset=utf-8");
        multipart.addBodyPart(greetingPart);
        multipart.addBodyPart(contentPart);

        return multipart;
    }

    private static double calculateTotalAmount(List<CartItem> cart) {
        return cart.stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
    }
}
