package context;

import dao.AccountDAO;
import entity.Account.User;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/*
 * @author tuanngp
 */
@WebListener
public class AccountListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        if ("user".equals(attributeName)) {
            User user = (User) event.getValue();
            System.out.println("Attribute 'user' added to session at " + System.currentTimeMillis() + " with value: " + user);
            AccountDAO.logUserLoginTime(user.getId());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        if ("user".equals(attributeName)) {
            User user = (User) event.getValue();
            System.out.println("Attribute 'user' remove to session at " + System.currentTimeMillis() + " with value: " + user);
            AccountDAO.addLogout(user.getId());
        }
    }
//
//    @Override
//    public void attributeReplaced(HttpSessionBindingEvent event) {
//        
//    }
}
