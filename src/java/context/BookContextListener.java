package context;

import dao.AccountDAO;
import dao.BookDAO;
import entity.Account.User;
import entity.Product.Book;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class BookContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize a list of books
        List<Book> bookList = BookDAO.getNewBooks(5);
        // Add the list of books as an attribute to the ServletContext
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("books", bookList);

        System.out.println("List of books added to the ServletContext.");
    }
    
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//
//    }
}
