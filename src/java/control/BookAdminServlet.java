package control;

import entity.Product.Author;
import entity.Product.Book;
import entity.Product.Publisher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "BookAdminServlet", urlPatterns = {"/bookAdmin"})
public class BookAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String msg = "";
        try {
            String action = request.getParameter("action");
            Author author = null;
            Publisher publisher = null;
            Book book = null;
            if (!action.equals("delete")) {
                // Get Author Information
                author = getAuthorFromRequest(request);

                // Get Publisher Information
                publisher = getPublisherFromRequest(request);

                // Get Book Information
                book = getBookFromRequest(request, author, publisher);
            }
//            out.print(author.addAuthor());
//            out.print(publisher.addPublisher());
//            out.print(book.addBook());

            switch (action) {
                case "add":
                    msg = add(book, author, publisher) ? "Thêm sách thành công" : "Đã xảy ra lỗi, thêm sách thất bại!";
                    break;
                case "update":
                    msg = update(book, author, publisher) ? "Cập nhật thông tin sách thành công!" : "Đã xảy ra lỗi, cập nhật thông tin sách thất bại!";
                    break;
                case "delete":
                    int authorId = Integer.parseInt(request.getParameter("authorId"));
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    int publisherId = Integer.parseInt(request.getParameter("publisherId"));

                    Book deleteBook = new Book(bookId);
                    Author deleteAuthor = new Author(authorId);
                    Publisher deletePublisher = new Publisher(publisherId);

                    boolean deleteSucess = deleteBook.delete() && deleteAuthor.delete() && deletePublisher.delete() ;

                    msg = deleteSucess ? "Xóa thành công!" : "Xóa thất bại!";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid action: " + action);
            }
        } catch (IllegalArgumentException e) {
        } finally {
            HttpSession session = request.getSession();
            session.setAttribute("statusAdmin", msg);
            response.sendRedirect("admin#books");
        }
    }

    private Author getAuthorFromRequest(HttpServletRequest request) {
        try {
            String idStr = request.getParameter("authorId").isEmpty() ? "0" : request.getParameter("authorId");
            int id = Integer.parseInt(idStr);
            String authorName = request.getParameter("authorName").isEmpty()?"UNKOWN":request.getParameter("authorName");
            String birthday = request.getParameter("birthday");
            String bio = request.getParameter("bio").isEmpty()?"UNKOWN":request.getParameter("bio");
            return new Author(id, authorName, birthday.isEmpty() ? Date.valueOf("1900-01-01") : Date.valueOf(birthday), bio);
        } catch (NumberFormatException e) {
        }
        return null;
    }

    private Publisher getPublisherFromRequest(HttpServletRequest request) {
        try {
            String idStr = request.getParameter("publisherId").isEmpty() ? "0" : request.getParameter("publisherId");
            int id = Integer.parseInt(idStr);
            String publisherName = request.getParameter("publisherName").isEmpty()?"UNKOWN":request.getParameter("publisherName");
            String establishedDate = request.getParameter("establishedDate");
            return new Publisher(id, publisherName, establishedDate.isEmpty() ? Date.valueOf("1900-01-01") : Date.valueOf(establishedDate));
        } catch (NumberFormatException e) {
        }
        return null;
    }

    private Book getBookFromRequest(HttpServletRequest request, Author author, Publisher publisher) {
        try {
            String idStr = request.getParameter("bookId").isEmpty() ? "0" : request.getParameter("bookId");
            int bookId = Integer.parseInt(idStr);
            String bookTitle = request.getParameter("bookTitle");
            String genre = request.getParameter("genre").isEmpty()?"UNKOWN":request.getParameter("genre");
            String description = request.getParameter("description").isEmpty()?"UNKOWN":request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float price = Float.parseFloat(request.getParameter("price"));
            String image = request.getParameter("image");
            return new Book(bookId, bookTitle, genre, description, quantity, price, author, publisher, image);
        } catch (NumberFormatException e) {
        }
        return null;
    }

    private boolean add(Book book, Author author, Publisher publisher) throws ServletException, IOException {
        return author != null && author.addAuthor() && publisher != null && publisher.addPublisher() && book != null && book.addBook();
    }

    private boolean update(Book b, Author author, Publisher publisher) {
        boolean updateSuccess = false;
        if (author != null) {
            author.update();
            updateSuccess = true;
        }

        if (publisher != null) {
            publisher.update();
            updateSuccess = true;
        }

        if (b != null) {
            b.update();
            updateSuccess = true;
        }
        return updateSuccess;
    }

}
